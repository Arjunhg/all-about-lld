package I_Concurrency_Problems.C_Bounded_Blocking_Queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    
    public static void main(String[] args) {
        try {

            // BoundedBlockingQueue_Synchronized queue = new BoundedBlockingQueue_Synchronized(5);
            // BoundedBlockingQueue_ReentrantLock queue = new BoundedBlockingQueue_ReentrantLock(5);
            BoundedBlockingQueue_Semaphore_and_Reentrant queue = new BoundedBlockingQueue_Semaphore_and_Reentrant(5);

            ExecutorService executor = Executors.newFixedThreadPool(10);

            // Producer
            for(int i=1; i<=10; i++){
                final int element = i;
                executor.submit(() -> {
                    try {
                        queue.enqueue(element);
                        System.out.println(Thread.currentThread().getName() + " enqueued " + element);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }, "Producer " + i);
            }

            Thread.sleep(500); // Wait for a while before starting consumers

            // Consumer
            for(int i=1; i<=10; i++){
                executor.submit(() -> {
                    try {
                        int dequeued = queue.dequeue();
                        System.out.println(Thread.currentThread().getName() + " dequeued " + dequeued);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }, "Consumer " + i);
            }

            Thread.sleep(2000); // Allow some time for processing

            System.out.println("Final size of queue: " + queue.size());

            executor.shutdown();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
