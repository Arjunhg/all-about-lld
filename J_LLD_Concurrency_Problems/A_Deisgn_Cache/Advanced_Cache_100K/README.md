# Advanced Cache - 100K Scale (10,000 - 100,000 Users)

## Problems at This Scale

### 1. Single LRU Lock Contention (Critical)
- Even with `ReentrantReadWriteLock`, a single lock becomes severe bottleneck
- 100,000 concurrent operations competing for one lock
- P99 latency can exceed 100ms during spikes

### 2. Cache Stampede
- When a popular key expires or is evicted(although eviction of popular key is not possible in LRU, LFU based eviction), all concurrent requests miss
- All threads simultaneously hit the database
- Creates cascading failures under load

### 3. Write-Behind Data Loss
- In-memory write queue lost on JVM crash
- Unbounded queue can cause OOM
- No acknowledgment of durable writes

### 4. Limited Observability
- Need integration with production monitoring (Prometheus, Datadog)
- Histograms instead of simple averages
- Health check endpoints for orchestration

---

## Solutions Implemented

### ShardedLRUEvictionAlgorithm.java
- Divides keys into N shards (default: 16)
- Each shard has independent lock and LRU list
- Reduces contention by N times
- Approximate global LRU (per-shard eviction)

### StampedeProtectedCache.java
- Per-key locking for cache population
- First thread to miss does the fetch, others wait
- Configurable timeout to prevent indefinite blocking
- Uses `Striped` locks for memory efficiency

### DurableWriteBehindPolicy.java
- Append-only write-ahead log (WAL) for durability
- Bounded queue with backpressure
- Acknowledgment only after WAL persistence
- Async batch flush to database

### PrometheusMetrics.java
- Histogram for latency distribution (P50, P95, P99)
- Gauge for current cache size
- Counter for operations by type
- Micrometer-compatible interface

---

## What Can Still Go Wrong

1. **Single-node limitations** - Still bounded by single machine resources
2. **WAL disk I/O** - Can become bottleneck under extreme write load
3. **Shard imbalance** - Hot keys in same shard still contend
4. **No distributed invalidation** - Other nodes don't see updates

---

## Tradeoffs Made

| Decision | Pro | Con |
|----------|-----|-----|
| 16 shards | Good balance for 100K | May need tuning for specific workloads |
| Per-key stampede locks | Prevents thundering herd | Memory overhead for lock map |
| Append-only WAL | Simple, durable | Requires periodic compaction |
| Striped locks (1024) | Bounded memory | Small collision probability |

---

## When to Move Beyond This

If you experience:
- Single machine cannot handle load (need horizontal scaling)
- Network partition requirements (need distributed cache)
- Cross-region data access (need replication)

Then proceed to `Advanced_Cache_1M` for distributed architecture guidance.
