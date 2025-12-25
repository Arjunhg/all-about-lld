package H_Concurrency.A_Thread_Runnable_Callable.C_Callable_Interface;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallableInteger {
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> c1 = new CallableIntegerExample();
        Callable<Integer> c2 = new CallableIntegerExample();

        // CallableIntegerExample c = new CallableIntegerExample();

        try {
            // Integer result1 = executor.submit(c1).get();
            // Integer result2 = executor.submit(c2).get();
            /* The above code won't give race condition becasue .get() blocks the main thread until c1.call() finishes completely. 
            */

            /* Race Condition In Action(using separate Callable instances): */
            Future<Integer> f1 = executor.submit(c1);
            Future<Integer> f2 = executor.submit(c2);

            /* Or use shared Callable */
            // Future<Integer> f1 = executor.submit(c);
            // Future<Integer> f2 = executor.submit(c);

            Integer result1 = f1.get();
            Integer result2 = f2.get();


            System.out.println("Result from Callable 1: " + result1);
            System.out.println("Result from Callable 2: " + result2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
