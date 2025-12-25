package H_Concurrency.A_Thread_Runnable_Callable.A_Thread_Class;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterFixed {
    
    static AtomicInteger counter = new AtomicInteger(0); // Use AtomicInteger to ensure atomicity of increment operation.

    public static void main(String[] args) {
        
        Thread t1 = new Thread(() -> {
            for(int i=0; i<1000; i++){
                counter.incrementAndGet();
            }
        }, "Worker-1");
        
        Thread t2 = new Thread(() -> {
            for(int i=0; i<1000; i++){
                counter.incrementAndGet();
            }
        }, "Worker-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter);
    }
}
