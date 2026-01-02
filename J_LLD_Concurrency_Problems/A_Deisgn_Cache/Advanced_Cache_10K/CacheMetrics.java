package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_10K;

import java.util.concurrent.atomic.LongAdder;

/**
 * Thread-safe cache metrics using LongAdder for high-throughput environments.
 * LongAdder is preferred over AtomicLong when contention is expected because
 * it maintains internal cells that reduce CAS contention.
 */
public class CacheMetrics {

    private final LongAdder hits = new LongAdder();
    private final LongAdder misses = new LongAdder();
    private final LongAdder evictions = new LongAdder();
    private final LongAdder writes = new LongAdder();
    private final LongAdder totalLatencyNanos = new LongAdder();
    private final LongAdder operationCount = new LongAdder();

    public void recordHit() {
        hits.increment();
    }

    public void recordMiss() {
        misses.increment();
    }

    public void recordEviction() {
        evictions.increment();
    }

    public void recordWrite() {
        writes.increment();
    }

    public void recordLatency(long nanos) {
        totalLatencyNanos.add(nanos);
        operationCount.increment();
    }

    // Getters for monitoring
    public long getHits() {
        return hits.sum();
    }

    public long getMisses() {
        return misses.sum();
    }

    public long getEvictions() {
        return evictions.sum();
    }

    public long getWrites() {
        return writes.sum();
    }

    /**
     * Calculate hit rate as a percentage.
     * Returns 0 if no operations have been performed.
     */
    public double getHitRate() {
        long totalHits = hits.sum();
        long totalMisses = misses.sum();
        long total = totalHits + totalMisses;
        if (total == 0)
            return 0.0;
        return (double) totalHits / total * 100.0;
    }

    /**
     * Calculate average latency in milliseconds.
     */
    public double getAverageLatencyMs() {
        long count = operationCount.sum();
        if (count == 0)
            return 0.0;
        return totalLatencyNanos.sum() / (double) count / 1_000_000.0;
    }

    /**
     * Reset all metrics. Useful for periodic reporting windows.
     */
    public void reset() {
        hits.reset();
        misses.reset();
        evictions.reset();
        writes.reset();
        totalLatencyNanos.reset();
        operationCount.reset();
    }

    @Override
    public String toString() {
        return String.format(
                "CacheMetrics{hits=%d, misses=%d, hitRate=%.2f%%, evictions=%d, writes=%d, avgLatencyMs=%.3f}",
                getHits(), getMisses(), getHitRate(), getEvictions(), getWrites(), getAverageLatencyMs());
    }
}
