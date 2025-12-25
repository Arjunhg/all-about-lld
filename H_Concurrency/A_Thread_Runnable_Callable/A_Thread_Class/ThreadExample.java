package H_Concurrency.A_Thread_Runnable_Callable.A_Thread_Class;

public class ThreadExample {
    public static void main(String[] args) {
        
        MyThread t1 = new MyThread();
        t1.start();

        MyThread t2 = new MyThread(); // Creating it after starting t1 doesn't mean t1 will complete the execution first. (Threads start might be deterministic but not their execution)
        t2.start();

        // System.out.println("Done"); // This will print before the threads complete their execution. Use t1.join() and t2.join() to wait for their completion before printing "Done".

        /* Thread and Runnable can't throw checked exception. Callable can.
           class MyRunnable implements/extends Runnable/Threads {
                @Override
                public void run() {
                    // This cannot throw checked exceptions
                    throw new IOException("Test"); // ❌ Compile error
                }
            }
        */

        /*
            If you need checked exceptions or return values, use Callable + Future.
            Runnable/Thread is only good for simple fire-and-forget tasks.
        */
    }
}

