package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_10K;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.EvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;

/**
 * Enhanced cache with metrics, TTL support, and graceful shutdown.
 * Designed for 1,000 - 10,000 concurrent users.
 * 
 * Key improvements over base Cache:
 * 1. Integrated metrics (hits, misses, latency)
 * 2. TTL support via CacheWithTTL wrapper
 * 3. Graceful shutdown with drain timeout
 * 4. Uses ConcurrentLRUEvictionAlgorithm for better concurrency
 */
public class EnhancedCache<K, V> {

    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;
    private final GracefulKeyBasedExecutor keyBasedExecutor;
    private final CacheMetrics metrics;

    public EnhancedCache(
            CacheStorage<K, V> cacheStorage,
            DBStorage<K, V> dbStorage,
            WritePolicy<K, V> writePolicy,
            EvictionAlgorithm<K> evictionAlgorithm,
            int numExecutors) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
        this.keyBasedExecutor = new GracefulKeyBasedExecutor(numExecutors);
        this.metrics = new CacheMetrics();
    }

    public CompletableFuture<V> accessData(K key) {
        long startTime = System.nanoTime();

        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if (!cacheStorage.containsKey(key)) {
                    metrics.recordMiss();
                    throw new Exception("Key not found in cache: " + key);
                }
                evictionAlgorithm.keyAccessed(key);
                V value = cacheStorage.get(key);
                metrics.recordHit();
                metrics.recordLatency(System.nanoTime() - startTime);
                return value;
            } catch (Exception e) {
                metrics.recordLatency(System.nanoTime() - startTime);
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> updateData(K key, V value) {
        long startTime = System.nanoTime();

        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if (cacheStorage.containsKey(key)) {
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                } else {
                    if (cacheStorage.size() >= cacheStorage.getCapacity()) {
                        K evictedKey = evictionAlgorithm.evictKey();
                        if (evictedKey != null) {
                            int currIdx = keyBasedExecutor.getExecutorIndexForKey(key);
                            int evictedIdx = keyBasedExecutor.getExecutorIndexForKey(evictedKey);
                            if (currIdx == evictedIdx) {
                                cacheStorage.remove(evictedKey);
                            } else {
                                CompletableFuture<Void> removalFuture = keyBasedExecutor.submitTask(evictedKey, () -> {
                                    try {
                                        cacheStorage.remove(evictedKey);
                                        return null;
                                    } catch (Exception e) {
                                        throw new CompletionException(e);
                                    }
                                });
                                removalFuture.join();
                            }
                            metrics.recordEviction();
                        }
                    }
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }
                metrics.recordWrite();
                metrics.recordLatency(System.nanoTime() - startTime);
                return null;
            } catch (Exception e) {
                metrics.recordLatency(System.nanoTime() - startTime);
                throw new CompletionException(e);
            }
        });
    }

    /**
     * Get current cache metrics for monitoring.
     */
    public CacheMetrics getMetrics() {
        return metrics;
    }

    /**
     * Get a snapshot of metrics as string for logging.
     */
    public String getMetricsSnapshot() {
        return metrics.toString();
    }

    /**
     * Graceful shutdown - waits for in-flight operations to complete.
     * 
     * @return true if all tasks completed, false if forced shutdown was needed
     */
    public boolean shutDown() {
        return keyBasedExecutor.shutDown();
    }

    /**
     * Force immediate shutdown.
     */
    public void shutDownNow() {
        keyBasedExecutor.shutDownNow();
    }
}
