package I_Concurrency_Problems.C_Bounded_Blocking_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class BoundedBlockingQueue_Semaphore_and_Synchronized {
    
    private final Queue<Integer> queue;
    private final Semaphore emptySlots;
    private final Semaphore filledSlots;
    private final Object lock = new Object();

    public BoundedBlockingQueue_Semaphore_and_Synchronized(int capacity) {
        this.queue = new LinkedList<>();
        this.emptySlots = new Semaphore(capacity);
        this.filledSlots = new Semaphore(0);
    }

    public void enqueue(int element) throws InterruptedException {
        emptySlots.acquire(); // controls when a thread may proceed.
        /*What it guarantees

            - At most capacity threads can pass this point without a matching release()
            - The queue will not exceed its capacity
            - Producers block when the queue is full

        What it does NOT guarantee ❌

            - It does not prevent multiple threads from modifying queue at the same time
            - It does not make queue.offer() atomic
            - It does not provide mutual exclusion
         */
        synchronized(lock){ // controls how shared data is safely accessed
            queue.offer(element);
        }
        filledSlots.release();
    }

    public int dequeue() throws InterruptedException {
        filledSlots.acquire();
        int value;
        synchronized(lock){
            value = queue.poll();
        }
        emptySlots.release();
        return value;
    }

    /* This is not correct for current design:
        public synchronized int size() {
            return queue.size();
        }

        - All accesses to shared mutable state must use the SAME lock. Different lock = no mutual exclusion
        - Making method synchronized means we synchronizes on 'this' (queue object) but other methods synchronize on 'lock' object.
    */

    public int size() {
        synchronized(lock){
            return queue.size();
        }
        /* Why not use semaphores for size too?
            - Ulike fizzbuzz or zeroevenodd, it is not something which gets print when it's turn comes.
            - It can be called anytime by any thread. size() doesn't wait for a condition or doesn't change the queue.
            - So we need mutual exclusion to read the shared mutable state (queue)(ME + memory visibility).
        
        */
    }
}
