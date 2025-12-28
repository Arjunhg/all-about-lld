package I_Concurrency_Problems.C_Bounded_Blocking_Queue;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue_Semaphore_and_Reentrant {
    
    private final Queue<Integer> queue;
    private final ReentrantLock lock;
    private final Semaphore emptySlots;
    private final Semaphore filledSlots;
    
    public BoundedBlockingQueue_Semaphore_and_Reentrant(int capacity){
      this.queue = new LinkedList<>();
      this.lock = new ReentrantLock();
      this.emptySlots = new Semaphore(10);
      this.filledSlots = new Semaphore(0);
    }
    
    public void enqueue(int element) throws InterruptedException {
      emptySlots.acquire(); // semaphore is handling the cappacity for us so we don't have to use while condition to check if queue is empty or full
      lock.lock();
      try{
        queue.offer(element);
        filledSlots.release();
        /* Why filled slots release is inside try block?
            - emptySlots.acquire() happen before any state change(modification to queue)
            - filledSlots.release() happens only after the element is successfully added.
            - If queue.offer throws exception, you must not release filledSlots otherwise the consumer will think an element exists when it doesn't.
        
        */
      }finally{
        lock.unlock();
        // filledSlots.release(); // creates a fake permit saying: "Hey! An element is available!"
      }
    }
    
    public int dequeue() throws InterruptedException {
      filledSlots.acquire();
      lock.lock();
      try{
        return queue.poll();
      }finally{
        lock.unlock();
        emptySlots.release();
        /* Why empty slots is in finally block instead of try block?
            - Once filledSlots.acquire() succeeds, an element must exist
            - poll() should not fail in well-designed queue
            - If it did fail (queue is already broken), releasing emptySlots in finally ensures that the system can recover and continue functioning. (avoid deadlock)
        
        */
      }
    }
    
    public int size() throws InterruptedException {
      lock.lock();
      try{
        return queue.size();
      }finally{
        lock.unlock();
      }
    }
}

/*
If lock.lock() in both producer and consumer is placed before their respective acquire() calls, the system can enter a deadlock state. Here's how:

    - Let's say emptySlots has 0 permits (queue is full) and filledSlots has 10 permits.
    - A producer thread arrives and wants to add an element. It executes lock.lock() and successfully acquires the lock. This same thread also tries to execute emptySlots.acquire(), but since there are 0 permits, it goes into waiting state.
        - Current producer state: holds lock and waiting on emptySlots.

    - Now, a consumer thread arrives and wants to remove an element. It tries to execute lock.lock(), but it can't acquire the lock because the producer thread is holding it. So, the consumer thread goes into waiting state.
        - Current consumer state: waiting on lock.

    - Now we have a deadlock:
        - The producer thread is waiting for emptySlots to have a permit (which will only happen when a consumer removes an element and releases a permit, but it's blocked).
        - The consumer thread is waiting for the lock to be released by the producer thread.
*/
