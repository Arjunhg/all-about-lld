package H_Concurrency.A_Thread_Runnable_Callable.A_Thread_Class;

public class CounterBug {

    static int counter = 0; // counter is static so that both threads share the same variable.

    public static void main(String[] args) {

        // CounterBug counterBug = new CounterBug(); //Use for non-static counter. For single objec both the thread uses this same object. So variable is still shared and race condition still occurs.

        // CounterBug counterBug1 = new CounterBug();
        // CounterBug counterBug2 = new CounterBug(); // Use for non-static counter. For two different objects both the thread uses different object. So variable is not shared and race condition does not occur.

        
        Thread t1 = new Thread(() -> {
            for(int i=0; i<1000; i++){
                // counterBug.counter++; //Use for non-static counter
                // counterBug1.counter++;
                counter++;
            }
        }, "Worker-1");

        Thread t2 = new Thread(() -> {
            for(int i=0; i<1000; i++){
                // counterBug.counter++;
                // counterBug2.counter++;
                counter++;
            }
        }, "Worker-2");

        // t1.start();
        // try {
        //     t1.join(); // we can make the execution order deterministic by making main thread wait for t1 to finish before starting t2. But this defeats the purpose of concurrency.
        //     // You might think that since join makes the main thead wait for t1 to finish why does the execution of t2 stops here? The reason is that join only makes the main thread wait for t1 to finish, it does not affect the execution of t2. However, since t2 is started only after t1 has finished, there is no overlap in their execution,
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // t2.start();
        // try {
        //     t2.join();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        t1.start();
        t2.start();

        try {
            t1.join(); // main thread waits for t1 to finish (t2 may still be running so we might get race condition)
            // If we add breakpoints on the increment line inside both threads, we observere that even though t1 is started first, counter start the increment from t2 (thread start order is deterministic but execution order is not)
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final Counter Value: " + counter);
        // System.out.println("Final Counter Value: " + counterBug.counter);
        // System.out.println("Final Counter Value: " + (counterBug1.counter + counterBug2.counter));
        
    }
}

/*
Use Watch feature in debugger to monitor counter variable changes in both threads:
1. For static varialble add: Thread.currentThread().getName() and counter
2. For non-static variable add: Thread.currentThread().getName() and counterBug.counter or counterBug1.counter and counterBug2.counter
*/
