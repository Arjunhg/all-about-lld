package J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms;

// Eviction policies like LRU and LFU is used when max capacity is reached and TTL is for custom expiration.

public interface EvictionAlgorithm<K> {
    
    //Notify the evinction algotithm that a key has been accessed
    void keyAccessed(K key) throws Exception;

    // Select and remove one key from the cache
    K evictKey() throws Exception;
}
