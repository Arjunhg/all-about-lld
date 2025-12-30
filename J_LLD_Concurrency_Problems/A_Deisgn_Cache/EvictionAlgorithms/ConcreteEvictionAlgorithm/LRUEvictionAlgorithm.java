package J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.ConcreteEvictionAlgorithm;

import java.util.HashMap;
import java.util.Map;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.EvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses.DoublyLinkedList;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses.DoublyLinkedListNode;

public class LRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {

    // doubly linked list to track LRU order
    private final DoublyLinkedList<K> dll;

    // map of key to it'snode in ddl
    private final Map<K, DoublyLinkedListNode<K>> keyToNodeMap;

    public LRUEvictionAlgorithm(){
        this.dll = new DoublyLinkedList<>();
        this.keyToNodeMap = new HashMap<>();
    }

    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if(keyToNodeMap.containsKey(key)){
            //move node to the tail (most recently used)
            DoublyLinkedListNode<K> node = keyToNodeMap.get(key);
            dll.detatchNode(node);
            dll.addNodeAtTail(node);
        }else{
            DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtTail(newNode);
            keyToNodeMap.put(key, newNode);
        }
    }

    @Override
    public synchronized K evictKey() throws Exception{
        // remove least recently used node from head
        DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
        if(nodeToEvict == null){
            return null;
        }

        K evictKey = nodeToEvict.getValue();
        dll.removeHead();
        keyToNodeMap.remove(evictKey);
        return evictKey;
    }
    
}
