package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Enums;

/**
 * Scaling strategies for rate limiter based on expected user load
 */
public enum ScalingStrategy {
    /**
     * In-memory storage with TTL-based cleanup
     * Best for: < 100K users
     * Memory: Low to Medium
     * Cleanup: Periodic removal of inactive users
     */
    IN_MEMORY_TTL,
    
    /**
     * In-memory LRU cache with fixed size limit
     * Best for: 100K - 1M users
     * Memory: Medium (capped by LRU size)
     * Cleanup: Automatic eviction of least recently used
     */
    IN_MEMORY_LRU,
    
    /**
     * Distributed Redis-based storage
     * Best for: > 1M users or distributed deployments
     * Memory: External (Redis)
     * Cleanup: Redis TTL and eviction policies
     */
    DISTRIBUTED_REDIS
}