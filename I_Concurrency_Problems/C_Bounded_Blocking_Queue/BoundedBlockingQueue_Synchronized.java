package I_Concurrency_Problems.C_Bounded_Blocking_Queue;

import java.util.LinkedList;
import java.util.Queue;

class BoundedBlockingQueue_Synchronized {
    
    private int n;

    private Queue<Integer> queue; // mutable shared state
    // ConcurrentLinkedList is not needed here since it's non blocking, has no capacity limit and no reliable size

    public BoundedBlockingQueue_Synchronized(int capacity){
        this.n = capacity;
        this.queue = new LinkedList<>();
    }

    synchronized void enqueue(int element) throws InterruptedException {
        while(queue.size() == n) wait();
        queue.offer(element);  // If we put lock here it will throw IllegalMonitorStateException because wait() and notifyAll() must be called from synchronized context
        notifyAll();
    }

    synchronized int dequeue() throws InterruptedException {
      while(queue.isEmpty()) wait();
      notifyAll();
      return queue.poll();
    }

    synchronized int size() throws InterruptedException {
      /*
      - Without synchronized, size() may return an outdated or inconsistent value — even after enqueue completes — due to lack of memory visibility guarantees. Synchronization ensures mutual exclusion and visibility, so size() observes a fully completed enqueue or dequeue.

      - But why size still refers stale queue after enqueue or dequeue completes?

      - Because modern CPUs have per-core caches, threads may read from registers or L12/L2 cache (not RAM as it's slow in comparison). This means Producer (enqueue) may be running in one core and Consumer (dequeue) in another core.

      - Overall this affects the visibility of shared mutable state across threads even after operation completes.

      - That's why we have to use synchronized with size to ensure visibility of latest state of queue. (notifyAll() does NOT update memory visibilty, it just wakes the thread that are waiting and does not flush cache and does not publish state)

      - Locks are what gurantee visibility of shared mutable state across threads.
        - A monitor unlock happens-before every subsequent monitor lock on the same monitor.
        - That means:

            In enqueue():
                synchronized enqueue() {
                    queue.add(x);   // write
                } // monitor unlock → FLUSH writes

            In size():
                synchronized size() {
                    return queue.size(); // monitor lock → REFRESH reads
                }
        - This forces:
            - Writes to be flushed to main memory
            - Other cores to invalidate stale cache lines

      - That's how synchronized (locks) ensure visibility of shared mutable state across threads.
    
      */
      return queue.size();
      // notifyAll() is NOT needed here
      // notifyAll() is only for threads waiting on a condition
    }
}
