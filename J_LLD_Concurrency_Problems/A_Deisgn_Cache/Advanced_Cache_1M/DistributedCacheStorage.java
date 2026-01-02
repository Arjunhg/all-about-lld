package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_1M;

import java.util.Map;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;

/**
 * Interface for distributed cache storage at 1M+ scale.
 * 
 * This interface extends CacheStorage to maintain compatibility with existing
 * code
 * while adding distributed-specific capabilities.
 * 
 * Implementations would typically wrap:
 * - Redis Cluster (via Jedis or Lettuce)
 * - Memcached (via spymemcached)
 * - Hazelcast (via native client)
 * 
 * Key design decisions:
 * 1. Node management for elastic scaling
 * 2. Health checks for load balancer integration
 * 3. Replication factor control for consistency/availability trade-off
 * 4. Per-node metrics for debugging hot spots
 */
public interface DistributedCacheStorage<K, V> extends CacheStorage<K, V> {

    // ==================== Node Management ====================

    /**
     * Add a new node to the cache cluster.
     * Keys will be rebalanced automatically via consistent hashing.
     * 
     * @param nodeAddress Address in format "host:port"
     */
    void addNode(String nodeAddress);

    /**
     * Remove a node from the cache cluster.
     * Keys from this node will be redistributed to remaining nodes.
     * 
     * @param nodeAddress Address in format "host:port"
     */
    void removeNode(String nodeAddress);

    /**
     * Get list of all active nodes in the cluster.
     */
    java.util.List<String> getNodes();

    // ==================== Health Checks ====================

    /**
     * Check if the cache cluster is healthy overall.
     * Returns true if minimum required nodes are available.
     */
    boolean isHealthy();

    /**
     * Get health status of each individual node.
     * Useful for debugging and determining which nodes need attention.
     * 
     * @return Map of node address to health status
     */
    Map<String, Boolean> getNodeHealth();

    /**
     * Get the node responsible for a specific key.
     * Useful for debugging key distribution.
     * 
     * @param key The key to locate
     * @return Address of the node holding this key
     */
    String getNodeForKey(K key);

    // ==================== Replication ====================

    /**
     * Get current replication factor.
     * A factor of 3 means each key is stored on 3 different nodes.
     */
    int getReplicationFactor();

    /**
     * Set replication factor for new entries.
     * Higher factor = better availability, more memory usage.
     * 
     * @param factor Number of replicas (typically 2 or 3)
     */
    void setReplicationFactor(int factor);

    // ==================== Consistency ====================

    /**
     * Read preference for distributed reads.
     */
    enum ReadPreference {
        PRIMARY, // Always read from primary (strongest consistency)
        PRIMARY_PREFERRED, // Read from primary if available, else replica
        SECONDARY, // Read from replica (eventual consistency, lower latency)
        NEAREST // Read from geographically nearest node
    }

    /**
     * Set read preference for subsequent operations.
     */
    void setReadPreference(ReadPreference preference);

    // ==================== Metrics ====================

    /**
     * Get metrics broken down by node.
     * Helps identify hot spots in the cluster.
     */
    Map<String, NodeMetrics> getNodeMetrics();

    /**
     * Metrics for a single node.
     */
    interface NodeMetrics {
        long getHits();

        long getMisses();

        long getEvictions();

        long getMemoryUsedBytes();

        long getConnections();

        double getLatencyP99Ms();
    }
}
