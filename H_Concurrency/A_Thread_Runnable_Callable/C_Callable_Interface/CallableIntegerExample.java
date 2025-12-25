package H_Concurrency.A_Thread_Runnable_Callable.C_Callable_Interface;

import java.util.concurrent.Callable;

public class CallableIntegerExample implements Callable<Integer> {
    
    private static int counter; // This will work with both separate and shared callable instances if race condition is to be demonstrated
    // private int counter; // Only works with shared callable instance if race condition is to be demonstrated

    @Override
    public Integer call() {
        for(int i=0; i<1000; i++){
            counter++;
        }
        return counter;
    }
}
