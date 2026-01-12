package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.CommonEnums.RateLimiterType;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Factory.RateLimiterFactory;

/*
ExecutorService executor = Executors.newFixedThreadPool(10);
    - This creates a shared thread pool with 10 threads and one shared queue. 
    - When we submit any task:
        executor.submit(() -> processRequest("alice"));  // Could run on Thread1
        executor.submit(() -> processRequest("alice"));  // Could run on Thread5  
        executor.submit(() -> processRequest("bob"));    // Could run on Thread2
        executor.submit(() -> processRequest("alice"));  // Could run on Thread1 again
    - Any thread can pick any task. There is NO gurantee that all task for "alice" go to same thread. This is why we need lock further down in LazyBucket implementation

Single-Threaded Executor Per Partition:
    - Instead of one shared pool, we create separate single-threaded executor for each user (partition)]    
        Partition 0: Queue [Task_A, Task_B, ...] → Thread0 (only this thread)
        Partition 1: Queue [Task_C, Task_D, ...] → Thread1 (only this thread)
        Partition 2: Queue [Task_E, Task_F, ...] → Thread2 (only this thread)
        ...
        Partition 9: Queue [Task_X, Task_Y, ...] → Thread9 (only this thread)
    - Now when we submit tasks for a specific user, we route them to the same single-threaded executor based on a hash of the user ID.
    - No locks needed for LazyBucket because all requests for same user go to same thread, so no concurrent access.

*/
public class RateLimiterControllerSingleThreadedExecutor {
    private final List<ExecutorService> partitions;
    private final int numPartitions;
    private final RateLimiterInterface rateLimiter;

    public RateLimiterControllerSingleThreadedExecutor(RateLimiterType type, Map<String, Object> config){
        this.rateLimiter = RateLimiterFactory.createLimiter(type, config);
        this.numPartitions = 10;
        this.partitions = new ArrayList<>();

        for(int i=0; i<numPartitions; i++){
            partitions.add(Executors.newSingleThreadExecutor());
        }
    }

    public CompletableFuture<Boolean> processRequest(String rateLimitKey){
        int partition = rateLimitKey == null ? 0 : getPartition(rateLimitKey); // null -> partition 0 (global bucket)
        return CompletableFuture.supplyAsync(() -> {
            return rateLimiter.giveAccess(rateLimitKey);
        }, partitions.get(partition));
    }

    private int getPartition(String key){
       return Math.abs(key.hashCode()) % numPartitions;
    }
}

/* Usage:

-> In Main.java => No need to create and pass shared executor to controller

-> In TokenBucketLazyRefillStrategy.java => No locks needed in LazyBucket class

*/
