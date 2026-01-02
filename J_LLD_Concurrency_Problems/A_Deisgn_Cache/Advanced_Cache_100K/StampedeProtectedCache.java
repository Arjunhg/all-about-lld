package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_100K;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;

/**
 * Cache wrapper that prevents cache stampede (thundering herd) problem.
 * 
 * Problem: When a popular key expires/evicts, all concurrent requests miss the
 * cache
 * and simultaneously hit the database, causing cascading load.
 * 
 * Solution: Per-key locking ensures only ONE thread fetches the value on a
 * miss.
 * All other threads wait for the first thread to populate the cache.
 * 
 * Implementation uses "Striped" lock approach:
 * - Don't allocate one lock per key (unbounded memory)
 * - Instead, hash keys to a fixed pool of locks (bounded memory, slight
 * collision risk)
 */
public class StampedeProtectedCache<K, V> {

    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final ConcurrentHashMap<Integer, ReentrantLock> stripedLocks;
    private final int numStripes;
    private final long loadTimeoutMs;

    /**
     * @param cacheStorage  Underlying cache storage
     * @param dbStorage     Database to fetch on miss
     * @param numStripes    Number of lock stripes (1024 is good default)
     * @param loadTimeoutMs Maximum time to wait for another thread's fetch
     *                      (prevents deadlock)
     */
    public StampedeProtectedCache(
            CacheStorage<K, V> cacheStorage,
            DBStorage<K, V> dbStorage,
            int numStripes,
            long loadTimeoutMs) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.stripedLocks = new ConcurrentHashMap<>();
        this.numStripes = numStripes;
        this.loadTimeoutMs = loadTimeoutMs;
    }

    public StampedeProtectedCache(CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) {
        this(cacheStorage, dbStorage, 1024, 5000); // Default: 1024 stripes, 5s timeout
    }

    private ReentrantLock getLockForKey(K key) {
        int stripeIndex = Math.floorMod(key.hashCode(), numStripes);
        return stripedLocks.computeIfAbsent(stripeIndex, k -> new ReentrantLock());
    }

    /**
     * Get value from cache, loading from DB on miss with stampede protection.
     * 
     * @param key The key to fetch
     * @return The value (from cache or DB)
     * @throws Exception if key not found in DB or timeout waiting for load
     */
    public V get(K key) throws Exception {
        // Fast path: cache hit (no locking needed)
        if (cacheStorage.containsKey(key)) {
            return cacheStorage.get(key);
        }

        // Slow path: cache miss - need to load from DB
        ReentrantLock lock = getLockForKey(key);

        boolean acquired = false;
        try {
            // Try to acquire lock with timeout
            acquired = lock.tryLock(loadTimeoutMs, TimeUnit.MILLISECONDS);

            if (!acquired) {
                // Timeout waiting for another thread's load - try cache one more time
                if (cacheStorage.containsKey(key)) {
                    return cacheStorage.get(key);
                }
                throw new Exception("Timeout waiting for cache load: " + key);
            }

            // Double-check: another thread might have loaded it while we waited
            if (cacheStorage.containsKey(key)) {
                return cacheStorage.get(key);
            }

            // We're the chosen one - load from DB and populate cache
            V value = dbStorage.read(key);
            cacheStorage.put(key, value);
            return value;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Interrupted while waiting for cache load: " + key);
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
    }

    /**
     * Get value with custom loader function (for when DB isn't the source).
     */
    public V getOrLoad(K key, Supplier<V> loader) throws Exception {
        if (cacheStorage.containsKey(key)) {
            return cacheStorage.get(key);
        }

        ReentrantLock lock = getLockForKey(key);
        boolean acquired = false;

        try {
            acquired = lock.tryLock(loadTimeoutMs, TimeUnit.MILLISECONDS);

            if (!acquired) {
                if (cacheStorage.containsKey(key)) {
                    return cacheStorage.get(key);
                }
                throw new Exception("Timeout waiting for cache load: " + key);
            }

            if (cacheStorage.containsKey(key)) {
                return cacheStorage.get(key);
            }

            V value = loader.get();
            if (value != null) {
                cacheStorage.put(key, value);
            }
            return value;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Interrupted while waiting for cache load: " + key);
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
    }

    /**
     * Async version with CompletableFuture.
     */
    public CompletableFuture<V> getAsync(K key) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return get(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Direct put bypasses stampede protection (writes don't stampede).
     */
    public void put(K key, V value) throws Exception {
        cacheStorage.put(key, value);
        dbStorage.write(key, value);
    }
}
