package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_1M;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Consistent hashing implementation for distributed cache node selection.
 * 
 * How it works:
 * 1. Each node is hashed to multiple points on a ring (virtual nodes)
 * 2. Each key is hashed to a point on the ring
 * 3. Key is assigned to the first node clockwise from its hash point
 * 
 * Benefits:
 * - Adding/removing nodes only affects K/N keys (minimal redistribution)
 * - Virtual nodes improve distribution uniformity
 * - Predictable O(log N) lookup time
 * 
 * This is how Redis Cluster, Cassandra, and DynamoDB distribute data.
 */
public class ConsistentHashRing<T> {

    private final SortedMap<Long, T> ring = new TreeMap<>();
    private final int virtualNodes; // Number of virtual nodes per physical node
    private final HashFunction hashFunction;

    public ConsistentHashRing(int virtualNodes) {
        this(virtualNodes, ConsistentHashRing::defaultHash);
    }

    public ConsistentHashRing(int virtualNodes, HashFunction hashFunction) {
        this.virtualNodes = virtualNodes;
        this.hashFunction = hashFunction;
    }

    /**
     * Add a node to the ring.
     * Creates multiple virtual nodes for better distribution.
     */
    public void addNode(T node) {
        for (int i = 0; i < virtualNodes; i++) {
            long hash = hashFunction.hash(node.toString() + "-vn-" + i);
            ring.put(hash, node);
        }
    }

    /**
     * Remove a node from the ring.
     */
    public void removeNode(T node) {
        for (int i = 0; i < virtualNodes; i++) {
            long hash = hashFunction.hash(node.toString() + "-vn-" + i);
            ring.remove(hash);
        }
    }

    /**
     * Get the node responsible for a key.
     * 
     * @param key The key to locate
     * @return The node responsible for this key, or null if ring is empty
     */
    public T getNodeForKey(Object key) {
        if (ring.isEmpty()) {
            return null;
        }

        long hash = hashFunction.hash(key.toString());

        // Find the first node clockwise from the key's hash
        SortedMap<Long, T> tailMap = ring.tailMap(hash);
        Long nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();

        return ring.get(nodeHash);
    }

    /**
     * Get N nodes responsible for a key (for replication).
     * Returns nodes in clockwise order around the ring.
     * 
     * @param key   The key to locate
     * @param count Number of nodes to return
     * @return List of nodes for replication
     */
    public List<T> getNodesForKey(Object key, int count) {
        if (ring.isEmpty()) {
            return new ArrayList<>();
        }

        List<T> nodes = new ArrayList<>();
        long hash = hashFunction.hash(key.toString());

        // Start from key's position, walk clockwise
        SortedMap<Long, T> tailMap = ring.tailMap(hash);

        for (T node : tailMap.values()) {
            if (!nodes.contains(node) && nodes.size() < count) {
                nodes.add(node);
            }
        }

        // Wrap around to beginning of ring if needed
        for (T node : ring.values()) {
            if (!nodes.contains(node) && nodes.size() < count) {
                nodes.add(node);
            }
        }

        return nodes;
    }

    /**
     * Get all physical nodes in the ring.
     */
    public List<T> getAllNodes() {
        return new ArrayList<>(new java.util.HashSet<>(ring.values()));
    }

    /**
     * Get the number of physical nodes.
     */
    public int size() {
        return ring.size() / virtualNodes;
    }

    /**
     * Hash function interface for flexibility.
     */
    @FunctionalInterface
    public interface HashFunction {
        long hash(String key);
    }

    /**
     * Default hash function using FNV-1a (fast, good distribution).
     */
    private static long defaultHash(String key) {
        // FNV-1a hash
        long hash = 0xcbf29ce484222325L;
        for (int i = 0; i < key.length(); i++) {
            hash ^= key.charAt(i);
            hash *= 0x100000001b3L;
        }
        return hash;
    }

    /**
     * Visualize the ring distribution for debugging.
     */
    public String visualize() {
        StringBuilder sb = new StringBuilder();
        sb.append("Consistent Hash Ring (").append(size()).append(" nodes, ");
        sb.append(virtualNodes).append(" virtual nodes each)\n");
        sb.append("==================================================\n");

        for (var entry : ring.entrySet()) {
            sb.append(String.format("Hash: %20d -> Node: %s%n", entry.getKey(), entry.getValue()));
            if (sb.length() > 2000) {
                sb.append("... (truncated)\n");
                break;
            }
        }

        return sb.toString();
    }
}
