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
        return CompletableFuture.supplyAsync(task, executor); // queue the task instead of sequentially executing it.
    }
    // Key is mapped to exactly one executor and that executor has ONE thread

    /* Why use completable future here?
    - Might be slow (DB, eviction, locking)
    - Should not block caller threads
    - Needs ordering guarantees per key
     */

    public int getExecutorIndexForKey(Object key){
        // return Math.abs(key.hashCode()) % numExecutors;
        /* hashCode returns an int. And int range from -2,147,483,648 to 2,147,483,647
        - Many standard hash functions internally uses: int hash = 31 * hash + fieldHash;
        - Overflow is expected. If it reaches Integer.MIN_VALUE, it's still negative because there is no positive counterpart for it.
        - Example:
            String s = "\u8000";   // a valid Unicode character
            int h = s.hashCode(); // h == Integer.MIN_VALUE
            - This is not theoretical — it happens.
        */
        return Math.floorMod(key.hashCode(), numExecutors);

    }

    public void shutDown(){
        for(ExecutorService executor : executors){
            executor.shutdown();
        }
    }
}
