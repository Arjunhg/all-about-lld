package J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses;

public class DoublyLinkedListNode<K> {
    
    private final K value;
    DoublyLinkedListNode<K> prev;
    DoublyLinkedListNode<K> next;

    public DoublyLinkedListNode(K value){
        this.value = value;
        prev = null;
        next = null;
    }

    public K getValue(){
        return value;
    }
}
