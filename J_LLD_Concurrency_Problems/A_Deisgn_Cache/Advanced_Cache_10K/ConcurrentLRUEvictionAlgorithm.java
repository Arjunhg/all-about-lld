package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_10K;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.EvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses.DoublyLinkedList;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses.DoublyLinkedListNode;

/**
 * Improved LRU eviction algorithm using ReentrantReadWriteLock instead of
 * synchronized.
 * 
 * Benefits over synchronized:
 * - Multiple threads can read concurrently (keyAccessed for existing keys)
 * - Write lock only needed for structural changes (eviction, new keys)
 * - Reduces contention significantly at 1K-10K concurrent users
 * 
 * Note: keyAccessed() still needs write lock because it modifies the list
 * structure.
 * True read-only operations would be things like "check if key exists" which we
 * don't expose.
 */
public class ConcurrentLRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {

    private final DoublyLinkedList<K> dll;
    private final Map<K, DoublyLinkedListNode<K>> keyToNodeMap;
    private final ReentrantReadWriteLock lock;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    public ConcurrentLRUEvictionAlgorithm() {
        this.dll = new DoublyLinkedList<>();
        this.keyToNodeMap = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    @Override
    public void keyAccessed(K key) throws Exception {
        // First try with read lock to check if key exists
        readLock.lock();
        try {
            if (keyToNodeMap.containsKey(key)) {
                // Key exists - need to upgrade to write lock for modification
                readLock.unlock();
                writeLock.lock();
                try {
                    // Double-check after acquiring write lock (another thread might have removed
                    // it)
                    if (keyToNodeMap.containsKey(key)) {
                        DoublyLinkedListNode<K> node = keyToNodeMap.get(key);
                        dll.detatchNode(node);
                        dll.addNodeAtTail(node);
                    } else {
                        // Key was removed between read and write lock - add it fresh
                        addNewKey(key);
                    }
                } finally {
                    writeLock.unlock();
                }
                return; // Early return since we already unlocked read lock
            }
        } finally {
            // Only unlock if we still hold the read lock (didn't upgrade)
            if (lock.getReadHoldCount() > 0) {
                readLock.unlock();
            }
        }

        // Key doesn't exist - acquire write lock to add
        writeLock.lock();
        try {
            // Double-check in case another thread added it
            if (!keyToNodeMap.containsKey(key)) {
                addNewKey(key);
            } else {
                // Another thread added it - just move to tail
                DoublyLinkedListNode<K> node = keyToNodeMap.get(key);
                dll.detatchNode(node);
                dll.addNodeAtTail(node);
            }
        } finally {
            writeLock.unlock();
        }
    }

    private void addNewKey(K key) {
        DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
        dll.addNodeAtTail(newNode);
        keyToNodeMap.put(key, newNode);
    }

    @Override
    public K evictKey() throws Exception {
        writeLock.lock();
        try {
            DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
            if (nodeToEvict == null) {
                return null;
            }

            K evictKey = nodeToEvict.getValue();
            dll.removeHead();
            keyToNodeMap.remove(evictKey);
            return evictKey;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Check if a key exists in the LRU tracking (read-only operation).
     */
    public boolean containsKey(K key) {
        readLock.lock();
        try {
            return keyToNodeMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get current size of LRU tracking (read-only operation).
     */
    public int size() {
        readLock.lock();
        try {
            return keyToNodeMap.size();
        } finally {
            readLock.unlock();
        }
    }
}
