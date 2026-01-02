package J_LLD_Concurrency_Problems.A_Deisgn_Cache.UtilityClasses;

public class DoublyLinkedList<K> {
    private DoublyLinkedListNode<K> head;
    private DoublyLinkedListNode<K> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addNodeAtTail(DoublyLinkedListNode<K> node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        node.next = null;
    }

    public void detatchNode(DoublyLinkedListNode<K> node) {
        if (node == null)
            return;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // Head node
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    public DoublyLinkedListNode<K> getHead() {
        return head;
    }

    public void removeHead() {
        if (head != null) {
            if (head.next != null) {
                head = head.next;
                head.prev = null;
            } else {
                head = null;
                tail = null;
            }
        }
    }
}
