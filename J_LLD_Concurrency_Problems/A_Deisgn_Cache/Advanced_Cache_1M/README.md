# Advanced Cache - 1M+ Scale (100,000+ Users)

## Why Single-Node Cache Fails at This Scale

At 1M+ users, a single-node in-memory cache hits fundamental limits:

### 1. Memory Constraints
- 1M entries × 1KB avg = 1GB minimum
- With overhead, realistically 4-10GB
- JVM GC pauses become significant (100ms+)

### 2. CPU Constraints
- Single machine has limited cores
- Even with sharding, lock contention exists
- Network I/O becomes bottleneck

### 3. Availability Requirements
- Single point of failure is unacceptable
- Need replication for high availability
- Maintenance requires downtime

### 4. Geographic Distribution
- Users in different regions need low latency
- Single datacenter can't serve globally
- Need edge caching or multi-region replication

---

## Distributed Cache Architectures

### Option 1: Redis Cluster

**Best for**: Most workloads, proven at scale

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│ App Server  │     │ App Server  │     │ App Server  │
└──────┬──────┘     └──────┬──────┘     └──────┬──────┘
       │                   │                   │
       └───────────┬───────┴───────────────────┘
                   │
         ┌─────────▼─────────┐
         │   Redis Client    │
         │ (Consistent Hash) │
         └─────────┬─────────┘
                   │
    ┌──────────────┼──────────────┐
    │              │              │
┌───▼───┐     ┌───▼───┐     ┌───▼───┐
│ Redis │     │ Redis │     │ Redis │
│ Node 1│     │ Node 2│     │ Node 3│
│Primary│     │Primary│     │Primary│
└───┬───┘     └───┬───┘     └───┬───┘
    │             │             │
┌───▼───┐     ┌───▼───┐     ┌───▼───┐
│Replica│     │Replica│     │Replica│
└───────┘     └───────┘     └───────┘
```

**Pros**:
- Built-in replication and failover
- Rich data structures (lists, sets, sorted sets)
- Lua scripting for atomic operations
- Well-documented, large community

**Cons**:
- Memory overhead (~2x for replication)
- Network latency added
- Operational complexity

### Option 2: Memcached

**Best for**: Simple key-value, maximum simplicity

**Pros**:
- Simpler than Redis
- Lower memory overhead
- Multi-threaded by design

**Cons**:
- No replication built-in
- No persistence
- Limited data types

### Option 3: Hazelcast (Java Native)

**Best for**: Java applications, embedded caching

```java
// Hazelcast configuration example
Config config = new Config();
config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
IMap<String, String> cache = hz.getMap("myCache");
```

**Pros**:
- Native Java integration
- Can run embedded or standalone
- Near-cache for hot data
- SQL-like queries

**Cons**:
- License considerations
- Heavier than Redis for simple use cases

---

## Consistent Hashing

At this scale, you need consistent hashing to distribute keys:

```
                     ┌─────────────────────────────────┐
                     │          Hash Ring              │
                     │                                 │
                     │       Node A (0-341)            │
                     │           ╱                     │
                     │          ╱                      │
                     │   ●─────●                       │
                     │  ╱       ╲                      │
                     │ ╱         ╲ Node B (342-682)    │
                     │●           ●                    │
                     │ ╲         ╱                     │
                     │  ╲       ╱                      │
                     │   ●─────●                       │
                     │          ╲                      │
                     │           ╲                     │
                     │       Node C (683-1023)         │
                     │                                 │
                     └─────────────────────────────────┘
```

**Benefits**:
- Adding/removing nodes only affects K/N keys (K = keys, N = nodes)
- Virtual nodes improve distribution
- Predictable key placement

---

## Cache Invalidation Strategies

### 1. TTL-Based Expiration
- Simplest approach
- Accept eventual consistency
- Good for: product catalogs, configuration

### 2. Event-Driven Invalidation
```
┌──────────┐     ┌─────────────┐     ┌─────────────┐
│ Database │────▶│ CDC/Events  │────▶│ Invalidator │
└──────────┘     └─────────────┘     └──────┬──────┘
                                            │
                      ┌─────────────────────┼─────────────────────┐
                      │                     │                     │
                ┌─────▼─────┐         ┌─────▼─────┐         ┌─────▼─────┐
                │  Cache 1  │         │  Cache 2  │         │  Cache 3  │
                └───────────┘         └───────────┘         └───────────┘
```

**Best for**: Inventory, pricing, user data

### 3. Write-Through to All Nodes
- Update all cache nodes on write
- Higher consistency, higher write latency
- Risk of partial failures

---

## CAP Theorem Considerations

At distributed scale, you must choose trade-offs:

| Guarantee | Cache Behavior |
|-----------|---------------|
| **CP** (Consistency + Partition Tolerance) | Reads may fail during partitions, but never stale |
| **AP** (Availability + Partition Tolerance) | Always readable, but may be stale (most common for cache) |

**For caching, AP is usually correct**: Stale data is acceptable; unavailability is not.

---

## Interface for Distributed Cache

The following interface maintains compatibility with existing code:

```java
public interface DistributedCacheStorage<K, V> extends CacheStorage<K, V> {
    
    // Node management
    void addNode(String nodeAddress);
    void removeNode(String nodeAddress);
    
    // Health
    boolean isHealthy();
    Map<String, Boolean> getNodeHealth();
    
    // Replication
    int getReplicationFactor();
    void setReplicationFactor(int factor);
    
    // Metrics per node
    Map<String, CacheMetrics> getNodeMetrics();
}
```

---

## Migration Path from Single-Node

1. **Phase 1**: Add Redis as a read-through cache beside existing code
2. **Phase 2**: Route reads to Redis, keep existing cache as fallback
3. **Phase 3**: Route writes through Redis
4. **Phase 4**: Remove single-node cache, use Redis only

**Key principle**: Run both in parallel, validate data matches, then cut over.

---

## What's NOT Included Here

This directory provides **concepts and interfaces**, not full implementations, because:

1. Distributed caching requires infrastructure (Redis cluster, network config)
2. Implementation details vary significantly by chosen technology
3. Production distributed caching needs operational runbooks, monitoring, etc.

For production:
- Use Redis Cluster or Amazon ElastiCache
- Use Hazelcast for embedded Java caching
- Consider Caffeine + Redis for two-tier caching
