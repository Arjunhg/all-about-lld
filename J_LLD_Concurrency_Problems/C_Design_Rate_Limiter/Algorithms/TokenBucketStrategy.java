package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketStrategy implements RateLimiterInterface {

    private final int bucketCapacity;
    private volatile int refreshRate; // tokens added per second
    // why volatile? because it can be updated via updateConfiguration method while other threads are reading it in refill method of Bucket class.

    // If no key provided, global rate limiting will be applied.
    private final Bucket globalBucket;

    // Use concurrent map to map each user bucket
    private final Map<String, Bucket> userBuckets;

    // executor service to schedule token refill at fixed rate
    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMs;

    //

    /*
        - Token bucket per user
        - Reentrant locks ensuring thread safe consumption and refill of tokens
    */
    private class Bucket {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int initialToken){
            this.tokens = initialToken;
        }

        /*
            - Consume one token if available
            - Use lock to ensure only one thread can consume token at a time
            - But each user has it's own bucket. Using ExecutorService pool we can map threads to users. Each user will get it's own unique thread. Which mean no two threads will access same user's bucket at same time. Because no two thraed map to same user.
            - So do we need lock here? 
        */
        public boolean tryConsume(){
            lock.lock();
            try{
                if(tokens > 0){
                    tokens--;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }

        public void refill(){
            lock.lock();
            try{
                tokens = Math.min(bucketCapacity, tokens + refreshRate); // only works for per second refill
            } finally {
                lock.unlock();
            }
        }
    }

    public TokenBucketStrategy(int bucketCapacity, int refreshRate){
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.refillIntervalMs = 1000; // refill every second
        this.globalBucket = new Bucket(bucketCapacity);
        this.userBuckets = new ConcurrentHashMap<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        startRefillTask();
    }

    // refill task to be scheduled at fixed rate
    private void startRefillTask(){
        scheduler.scheduleAtFixedRate(() -> {
            globalBucket.refill();
            for(Bucket bucket : userBuckets.values()){
                bucket.refill();
            }
        }, refillIntervalMs, refillIntervalMs, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public boolean giveAccess(String rateLimitKey){
        if(rateLimitKey != null && !rateLimitKey.isEmpty()){
            Bucket bucket = userBuckets.computeIfAbsent(rateLimitKey, k -> new Bucket(bucketCapacity));
            return bucket.tryConsume();
        }else{
            return globalBucket.tryConsume();
        }
    }

    @Override
    public void updateConfiguration(Map<String, Object> config){
        if(config.containsKey("refreshRate")){
            this.refreshRate = (int)config.get("refreshRate");
        }
    }

    @Override
    public void shutdown(){
        scheduler.shutdown();
    }
}
