package H_Concurrency.A_Thread_Runnable_Callable.C_Callable_Interface;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableStringExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> c1 = new MyCallableString("Task 1");
        Callable<String> c2 = new MyCallableString("Task 2");
        

        try{

            Future<String> f1 = executor.submit(c1);
            Future<String> f2 = executor.submit(c2);

            System.out.println("Result from Task 1:\n" + f1.get());
            System.out.println("Result from Task 2:\n" + f2.get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
