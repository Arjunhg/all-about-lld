# Advanced Cache - 10K Scale (1,000 - 10,000 Users)

## Problems at This Scale

### 1. No Observability
- Cannot measure cache hit/miss rates
- No visibility into eviction frequency  
- Cannot diagnose performance issues
- No data for capacity planning

### 2. No TTL Support
- Stale data persists indefinitely
- Only LRU eviction available
- Cache pollution with outdated entries

### 3. Synchronized LRU Contention
- Global lock on `synchronized` methods becomes noticeable
- 10-50ms latency spikes under moderate load
- `ReentrantReadWriteLock` reduces contention

### 4. Ungraceful Shutdown
- In-flight tasks may be lost
- No drain period for pending operations

---

## Solutions Implemented

### CacheMetrics.java
- Thread-safe counters using `LongAdder` (better than AtomicLong for high contention)
- Tracks: hits, misses, evictions, writes, latency
- `getHitRate()` method for monitoring
- Minimal performance overhead

### CacheWithTTL.java
- Wrapper around base cache with TTL support
- Lazy expiration: checks on access, removes if expired
- Background cleanup thread (configurable interval)
- Uses `ConcurrentHashMap` for expiration timestamps

### ConcurrentLRUEvictionAlgorithm.java
- Replaces `synchronized` with `ReentrantReadWriteLock`
- Read operations (keyAccessed for existing keys) use read lock
- Write operations (evict, add new) use write lock
- Multiple readers can proceed concurrently

### GracefulKeyBasedExecutor.java
- `shutdown()` → `awaitTermination(timeout)`
- Configurable drain timeout
- Logs warning if tasks don't complete in time

---

## What Can Still Go Wrong

1. **Over-eviction race** - Still possible (acceptable at this scale)
2. **TTL granularity** - Lazy expiration means slightly stale reads possible
3. **No distributed invalidation** - Single-node only
4. **Memory pressure** - Large values still held until eviction

---

## Tradeoffs Made

| Decision | Pro | Con |
|----------|-----|-----|
| LongAdder for metrics | Lock-free, high throughput | Slightly more memory |
| Lazy TTL expiration | No background overhead | Slightly delayed expiration |
| ReadWriteLock LRU | Better read concurrency | More complex than synchronized |
| 30s drain timeout | Allows in-flight completion | Slower shutdown |
