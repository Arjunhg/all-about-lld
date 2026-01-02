package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_100K;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.EvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses.DoublyLinkedList;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses.DoublyLinkedListNode;

/**
 * Sharded LRU eviction algorithm for high-concurrency environments (100K+
 * users).
 * 
 * Key insight: Instead of one global LRU with one lock, we maintain N
 * independent
 * LRU structures, each with its own lock. This reduces contention by N times.
 * 
 * Trade-off: Not a perfect global LRU. Each shard evicts its own
 * least-recently-used
 * key, which may not be the globally least-recently-used. This is acceptable
 * because:
 * 1. At 100K scale, strict LRU is less important than performance
 * 2. The approximation is still very good (hot keys stay, cold keys evict)
 * 3. Many production caches (memcached, Redis cluster) use similar approaches
 */
public class ShardedLRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {

    private final int numShards;
    private final List<LRUShard<K>> shards;

    public ShardedLRUEvictionAlgorithm() {
        this(16); // Default 16 shards - good balance for most workloads
    }

    public ShardedLRUEvictionAlgorithm(int numShards) {
        this.numShards = numShards;
        this.shards = new ArrayList<>(numShards);
        for (int i = 0; i < numShards; i++) {
            shards.add(new LRUShard<>());
        }
    }

    private LRUShard<K> getShardForKey(K key) {
        int shardIndex = Math.floorMod(key.hashCode(), numShards);
        return shards.get(shardIndex);
    }

    @Override
    public void keyAccessed(K key) throws Exception {
        LRUShard<K> shard = getShardForKey(key);
        shard.keyAccessed(key);
    }

    @Override
    public K evictKey() throws Exception {
        // Round-robin eviction across shards
        // This ensures no single shard is always evicted from
        LRUShard<K> shardWithOldest = null;
        long oldestAccessTime = Long.MAX_VALUE;

        for (LRUShard<K> shard : shards) {
            long headTime = shard.getHeadAccessTime();
            if (headTime < oldestAccessTime && headTime != -1) {
                oldestAccessTime = headTime;
                shardWithOldest = shard;
            }
        }

        if (shardWithOldest != null) {
            return shardWithOldest.evictKey();
        }
        return null;
    }

    /**
     * Get total size across all shards.
     */
    public int size() {
        int total = 0;
        for (LRUShard<K> shard : shards) {
            total += shard.size();
        }
        return total;
    }

    /**
     * Individual LRU shard with its own lock.
     */
    private static class LRUShard<K> {
        private final DoublyLinkedList<K> dll;
        private final Map<K, DoublyLinkedListNode<K>> keyToNodeMap;
        private final Map<K, Long> keyAccessTimes; // For eviction ordering across shards
        private final ReentrantLock lock;

        LRUShard() {
            this.dll = new DoublyLinkedList<>();
            this.keyToNodeMap = new HashMap<>();
            this.keyAccessTimes = new HashMap<>();
            this.lock = new ReentrantLock();
        }

        void keyAccessed(K key) {
            lock.lock();
            try {
                long now = System.nanoTime();
                if (keyToNodeMap.containsKey(key)) {
                    DoublyLinkedListNode<K> node = keyToNodeMap.get(key);
                    dll.detatchNode(node);
                    dll.addNodeAtTail(node);
                } else {
                    DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
                    dll.addNodeAtTail(newNode);
                    keyToNodeMap.put(key, newNode);
                }
                keyAccessTimes.put(key, now);
            } finally {
                lock.unlock();
            }
        }

        K evictKey() {
            lock.lock();
            try {
                DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
                if (nodeToEvict == null) {
                    return null;
                }

                K evictKey = nodeToEvict.getValue();
                dll.removeHead();
                keyToNodeMap.remove(evictKey);
                keyAccessTimes.remove(evictKey);
                return evictKey;
            } finally {
                lock.unlock();
            }
        }

        long getHeadAccessTime() {
            lock.lock();
            try {
                DoublyLinkedListNode<K> head = dll.getHead();
                if (head == null)
                    return -1;
                Long time = keyAccessTimes.get(head.getValue());
                return time != null ? time : -1;
            } finally {
                lock.unlock();
            }
        }

        int size() {
            lock.lock();
            try {
                return keyToNodeMap.size();
            } finally {
                lock.unlock();
            }
        }
    }
}
