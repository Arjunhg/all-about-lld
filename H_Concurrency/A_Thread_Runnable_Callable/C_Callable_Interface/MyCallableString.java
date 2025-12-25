package H_Concurrency.A_Thread_Runnable_Callable.C_Callable_Interface;

import java.util.concurrent.Callable;

public class MyCallableString implements Callable<String> {
    
    private final String name;

    public MyCallableString(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<5; i++){
            sb.append("Callable").append(name).append(" is running: ").append(i).append("\n");
            // Thread.sleep(500);
        }
        return sb.toString();
    }
}
