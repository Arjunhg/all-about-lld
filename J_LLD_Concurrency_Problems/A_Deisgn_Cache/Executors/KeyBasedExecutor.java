package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyBasedExecutor {
    
    private final ExecutorService[] executors;
    private final int numExecutors;

    public KeyBasedExecutor(int numExecutors){
        this.numExecutors = numExecutors;
        this.executors = new ExecutorService[numExecutors];
        for(int i=0; i<numExecutors; i++){
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    // Submit a task associated with a specific key so that all tasks for the same key are executed on the same single thread executor
    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task){
        int idx = getExecutorIndexForKey(key);
        ExecutorService executor = executors[idx];
        return CompletableFuture.supplyAsync(task, executor);
    }

    public int getExecutorIndexForKey(Object key){
        return Math.abs(key.hashCode()) % numExecutors;
    }

    public void shutDown(){
        for(ExecutorService executor : executors){
            executor.shutdown();
        }
    }
}
