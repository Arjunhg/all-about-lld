package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_10K;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;

/**
 * TTL-aware wrapper around any CacheStorage implementation.
 * 
 * Uses lazy expiration + background cleanup:
 * - Lazy: On each get(), checks if entry is expired and removes it
 * - Background: Periodic cleanup thread removes expired entries (reduces memory
 * waste)
 * 
 * This approach balances:
 * - Immediate staleness detection (lazy)
 * - Memory efficiency (background cleanup)
 * - Performance (no lock on every access beyond underlying storage)
 */
public class CacheWithTTL<K, V> implements CacheStorage<K, V> {

    private final CacheStorage<K, V> delegate;
    private final Map<K, Long> expirationTimes; // Key -> expiration timestamp in millis
    private final long defaultTTLMillis;
    private final ScheduledExecutorService cleanupExecutor;

    /**
     * @param delegate               The underlying cache storage
     * @param defaultTTLMillis       Default time-to-live for entries in
     *                               milliseconds
     * @param cleanupIntervalSeconds How often to run background cleanup (0 to
     *                               disable)
     */
    public CacheWithTTL(CacheStorage<K, V> delegate, long defaultTTLMillis, long cleanupIntervalSeconds) {
        this.delegate = delegate;
        this.expirationTimes = new ConcurrentHashMap<>();
        this.defaultTTLMillis = defaultTTLMillis;

        if (cleanupIntervalSeconds > 0) {
            this.cleanupExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "cache-ttl-cleanup");
                t.setDaemon(true); // Don't prevent JVM shutdown
                return t;
            });
            cleanupExecutor.scheduleAtFixedRate(
                    this::cleanupExpired,
                    cleanupIntervalSeconds,
                    cleanupIntervalSeconds,
                    TimeUnit.SECONDS);
        } else {
            this.cleanupExecutor = null;
        }
    }

    @Override
    public void put(K key, V value) throws Exception {
        delegate.put(key, value);
        expirationTimes.put(key, System.currentTimeMillis() + defaultTTLMillis);
    }

    /**
     * Put with custom TTL for this specific entry.
     */
    public void putWithTTL(K key, V value, long ttlMillis) throws Exception {
        delegate.put(key, value);
        expirationTimes.put(key, System.currentTimeMillis() + ttlMillis);
    }

    @Override
    public V get(K key) throws Exception {
        // Lazy expiration check
        Long expiration = expirationTimes.get(key);
        if (expiration != null && System.currentTimeMillis() > expiration) {
            // Entry has expired - remove it
            remove(key);
            throw new Exception("Key expired: " + key);
        }
        return delegate.get(key);
    }

    @Override
    public void remove(K key) throws Exception {
        expirationTimes.remove(key);
        delegate.remove(key);
    }

    @Override
    public boolean containsKey(K key) throws Exception {
        // Check expiration before reporting containment
        Long expiration = expirationTimes.get(key);
        if (expiration != null && System.currentTimeMillis() > expiration) {
            // Expired - clean it up
            try {
                remove(key);
            } catch (Exception ignored) {
                // Key might already be removed
            }
            return false;
        }
        return delegate.containsKey(key);
    }

    @Override
    public int size() throws Exception {
        return delegate.size();
    }

    @Override
    public int getCapacity() {
        return delegate.getCapacity();
    }

    /**
     * Background cleanup of expired entries.
     * Called periodically by the scheduled executor.
     */
    private void cleanupExpired() {
        long now = System.currentTimeMillis();
        for (Map.Entry<K, Long> entry : expirationTimes.entrySet()) {
            if (entry.getValue() < now) {
                try {
                    remove(entry.getKey());
                } catch (Exception ignored) {
                    // Key might already be removed by concurrent access
                }
            }
        }
    }

    /**
     * Shutdown the background cleanup thread.
     * Should be called when cache is no longer needed.
     */
    public void shutdown() {
        if (cleanupExecutor != null) {
            cleanupExecutor.shutdown();
            try {
                if (!cleanupExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    cleanupExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                cleanupExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Get time remaining until expiration for a key.
     * Returns -1 if key doesn't exist or is already expired.
     */
    public long getTimeToLive(K key) {
        Long expiration = expirationTimes.get(key);
        if (expiration == null)
            return -1;
        long remaining = expiration - System.currentTimeMillis();
        return remaining > 0 ? remaining : -1;
    }
}
