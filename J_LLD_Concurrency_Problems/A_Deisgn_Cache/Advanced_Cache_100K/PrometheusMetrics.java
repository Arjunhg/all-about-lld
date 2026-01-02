package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_100K;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.Map;

/**
 * Production-grade metrics compatible with monitoring systems like Prometheus.
 * 
 * In a real application, you'd use Micrometer or Prometheus Java client
 * directly.
 * This implementation demonstrates the same concepts with pure Java.
 * 
 * Key metrics for cache monitoring:
 * - Hit rate: Primary indicator of cache effectiveness
 * - Latency histograms: P50, P95, P99 for SLA monitoring
 * - Eviction rate: Indicates capacity issues
 * - Size gauge: For capacity planning
 */
public class PrometheusMetrics {

    // Counters (monotonically increasing)
    private final LongAdder cacheHitsTotal = new LongAdder();
    private final LongAdder cacheMissesTotal = new LongAdder();
    private final LongAdder cacheEvictionsTotal = new LongAdder();
    private final LongAdder cacheWritesTotal = new LongAdder();
    private final LongAdder cacheErrorsTotal = new LongAdder();

    // Histogram buckets for latency (in milliseconds)
    private static final double[] LATENCY_BUCKETS = { 1, 5, 10, 25, 50, 100, 250, 500, 1000, 2500 };
    private final LongAdder[] latencyBuckets = new LongAdder[LATENCY_BUCKETS.length + 1];
    private final LongAdder latencySum = new LongAdder();
    private final LongAdder latencyCount = new LongAdder();

    // Gauges (current value)
    private volatile long currentCacheSize = 0;
    private volatile long currentCacheCapacity = 0;

    // Labels for debugging
    private final String cacheName;
    private final Map<String, String> labels;

    public PrometheusMetrics(String cacheName) {
        this.cacheName = cacheName;
        this.labels = new ConcurrentHashMap<>();
        labels.put("cache_name", cacheName);

        // Initialize histogram buckets
        for (int i = 0; i < latencyBuckets.length; i++) {
            latencyBuckets[i] = new LongAdder();
        }
    }

    // Counter increments
    public void recordHit() {
        cacheHitsTotal.increment();
    }

    public void recordMiss() {
        cacheMissesTotal.increment();
    }

    public void recordEviction() {
        cacheEvictionsTotal.increment();
    }

    public void recordWrite() {
        cacheWritesTotal.increment();
    }

    public void recordError() {
        cacheErrorsTotal.increment();
    }

    /**
     * Record latency and update histogram.
     * 
     * @param latencyMs Latency in milliseconds
     */
    public void recordLatency(double latencyMs) {
        latencySum.add((long) (latencyMs * 1000)); // Store as micros for precision
        latencyCount.increment();

        // Find bucket
        for (int i = 0; i < LATENCY_BUCKETS.length; i++) {
            if (latencyMs <= LATENCY_BUCKETS[i]) {
                latencyBuckets[i].increment();
                return;
            }
        }
        // +Inf bucket
        latencyBuckets[LATENCY_BUCKETS.length].increment();
    }

    // Gauge updates
    public void setCacheSize(long size) {
        this.currentCacheSize = size;
    }

    public void setCacheCapacity(long capacity) {
        this.currentCacheCapacity = capacity;
    }

    // Metric getters
    public long getHitsTotal() {
        return cacheHitsTotal.sum();
    }

    public long getMissesTotal() {
        return cacheMissesTotal.sum();
    }

    public long getEvictionsTotal() {
        return cacheEvictionsTotal.sum();
    }

    public long getWritesTotal() {
        return cacheWritesTotal.sum();
    }

    public long getErrorsTotal() {
        return cacheErrorsTotal.sum();
    }

    public long getCacheSize() {
        return currentCacheSize;
    }

    public long getCacheCapacity() {
        return currentCacheCapacity;
    }

    /**
     * Calculate hit rate as percentage.
     */
    public double getHitRate() {
        long hits = cacheHitsTotal.sum();
        long total = hits + cacheMissesTotal.sum();
        return total == 0 ? 0 : (double) hits / total * 100;
    }

    /**
     * Get average latency in milliseconds.
     */
    public double getAverageLatencyMs() {
        long count = latencyCount.sum();
        return count == 0 ? 0 : latencySum.sum() / 1000.0 / count;
    }

    /**
     * Export metrics in Prometheus format.
     * Can be exposed via HTTP endpoint for scraping.
     */
    public String toPrometheusFormat() {
        StringBuilder sb = new StringBuilder();
        String prefix = "cache_";

        // Help and type declarations
        sb.append(String.format("# HELP %shits_total Total cache hits%n", prefix));
        sb.append(String.format("# TYPE %shits_total counter%n", prefix));
        sb.append(String.format("%shits_total{cache_name=\"%s\"} %d%n", prefix, cacheName, getHitsTotal()));

        sb.append(String.format("# HELP %smisses_total Total cache misses%n", prefix));
        sb.append(String.format("# TYPE %smisses_total counter%n", prefix));
        sb.append(String.format("%smisses_total{cache_name=\"%s\"} %d%n", prefix, cacheName, getMissesTotal()));

        sb.append(String.format("# HELP %sevictions_total Total cache evictions%n", prefix));
        sb.append(String.format("# TYPE %sevictions_total counter%n", prefix));
        sb.append(String.format("%sevictions_total{cache_name=\"%s\"} %d%n", prefix, cacheName, getEvictionsTotal()));

        sb.append(String.format("# HELP %swrites_total Total cache writes%n", prefix));
        sb.append(String.format("# TYPE %swrites_total counter%n", prefix));
        sb.append(String.format("%swrites_total{cache_name=\"%s\"} %d%n", prefix, cacheName, getWritesTotal()));

        sb.append(String.format("# HELP %serrors_total Total cache errors%n", prefix));
        sb.append(String.format("# TYPE %serrors_total counter%n", prefix));
        sb.append(String.format("%serrors_total{cache_name=\"%s\"} %d%n", prefix, cacheName, getErrorsTotal()));

        sb.append(String.format("# HELP %ssize Current cache size%n", prefix));
        sb.append(String.format("# TYPE %ssize gauge%n", prefix));
        sb.append(String.format("%ssize{cache_name=\"%s\"} %d%n", prefix, cacheName, getCacheSize()));

        sb.append(String.format("# HELP %scapacity Cache capacity%n", prefix));
        sb.append(String.format("# TYPE %scapacity gauge%n", prefix));
        sb.append(String.format("%scapacity{cache_name=\"%s\"} %d%n", prefix, cacheName, getCacheCapacity()));

        sb.append(String.format("# HELP %shit_rate Cache hit rate percentage%n", prefix));
        sb.append(String.format("# TYPE %shit_rate gauge%n", prefix));
        sb.append(String.format("%shit_rate{cache_name=\"%s\"} %.2f%n", prefix, cacheName, getHitRate()));

        // Histogram
        sb.append(String.format("# HELP %slatency_ms Latency histogram%n", prefix));
        sb.append(String.format("# TYPE %slatency_ms histogram%n", prefix));

        long cumulative = 0;
        for (int i = 0; i < LATENCY_BUCKETS.length; i++) {
            cumulative += latencyBuckets[i].sum();
            sb.append(String.format("%slatency_ms_bucket{cache_name=\"%s\",le=\"%.0f\"} %d%n",
                    prefix, cacheName, LATENCY_BUCKETS[i], cumulative));
        }
        cumulative += latencyBuckets[LATENCY_BUCKETS.length].sum();
        sb.append(String.format("%slatency_ms_bucket{cache_name=\"%s\",le=\"+Inf\"} %d%n",
                prefix, cacheName, cumulative));
        sb.append(String.format("%slatency_ms_sum{cache_name=\"%s\"} %.3f%n",
                prefix, cacheName, latencySum.sum() / 1000.0));
        sb.append(String.format("%slatency_ms_count{cache_name=\"%s\"} %d%n",
                prefix, cacheName, latencyCount.sum()));

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format(
                "CacheMetrics[%s]{hits=%d, misses=%d, hitRate=%.2f%%, evictions=%d, writes=%d, errors=%d, size=%d/%d, avgLatencyMs=%.3f}",
                cacheName, getHitsTotal(), getMissesTotal(), getHitRate(),
                getEvictionsTotal(), getWritesTotal(), getErrorsTotal(),
                getCacheSize(), getCacheCapacity(), getAverageLatencyMs());
    }
}
