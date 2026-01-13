package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* 
In TokenBucketStrategy we were using a scheduled task to refill tokens at fixed intervals:
    - This is eager refill strategy.
    - It works well for global bucket where we have single bucket shared across all requests.
    - But for bucket per user scenario, it is inefficient.
        - Suppose we have 1 million users, this will cause 1 million lock aquisitions every refresh interval.
        - Many of the bucket remains inactice, but still gets their tokens refilled.
        - That's O(n) where n is number of users.

In TokenBucketLazyRefillStrategy"
    - We use lazy refill strategy.
    - Global bucket can still use eager refill strategy.
    - But for bucket per user, only active users trigger refill calulations
    - If only k users are active in a refresh interval, only k buckets are updated.
    - That's O(k), (If 10,000 users are active out of 1 million -> 99% reduction in work)
    - Locks are still present on each bucket because multiple requests for same user can come concurrently.

*/

public class TokenBucketLazyRefillStrategy implements RateLimiterInterface{

    private final Bucket globalBucket;
    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMs;

    private final int bucketCapacity;
    private volatile int refreshRate;

    private final Map<String, LazyBucket> userBuckets;

    // Bucket for global.
    private class Bucket{
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int tokens){
            this.tokens = tokens;
        }

        private boolean tryConsume(){
            lock.lock();
            try{
                if(tokens>0){
                    tokens--;
                    return true;
                }
                return false;
            }finally{
                lock.unlock();
            }
        }
        private void refill(){
            lock.lock();
            try{
                tokens = Math.min(bucketCapacity, tokens + refreshRate);
            }finally{
                lock.unlock();
            }
        }
    }

    /*
        - Bucket for per user rate limiting
        - Tokens are calculate on-demand based on elasped time since last refill
        - It only exists when the user exists, so we don't initialize it in class constructor
    */
    private class LazyBucket{
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();
        private long lastRefillTimestamp;

        public LazyBucket(int tokens){
            this.tokens = tokens;
            this.lastRefillTimestamp = System.currentTimeMillis();
        }

        private boolean tryConsume(){
            lock.lock();
            try{
                // calulate token based on time elapsed
                refillLazily();
                if(tokens>0){
                    tokens--;
                    return true;
                }
                return false;
            }finally{
                lock.unlock();
            }
        }

        // On-demand refill
        private void refillLazily(){
            long currentTime = System.currentTimeMillis();
            long elaspedTime = currentTime - lastRefillTimestamp;

            if(elaspedTime > 0){
                long tokensToAdd = (elaspedTime * refreshRate) / 1000;
                
                if(tokensToAdd > 0){
                    tokens = Math.min(bucketCapacity, tokens + (int)tokensToAdd);
                    lastRefillTimestamp = currentTime;
                }
            }
        } 

        /*
            - Get last refill time for this bucket. It will be usefull for TTL based eviction
        */
        private long getLastRefillTimestamp(){
            lock.lock();
            try{
                return lastRefillTimestamp;
            }finally{
                lock.unlock();
            }
        }
    }

    public TokenBucketLazyRefillStrategy(int bucketCapacity, int refreshRate){
        this.globalBucket = new Bucket(bucketCapacity);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.refillIntervalMs = 1000; 
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.userBuckets = new ConcurrentHashMap<>();
        startRefillTask();
    }

    /*
        - Scheduler now only refills the global bucket
    */
    private void startRefillTask(){
        scheduler.scheduleAtFixedRate(() -> {
            globalBucket.refill();
        }, refillIntervalMs, refillIntervalMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean giveAccess(String rateLimitKey){
        if(rateLimitKey != null && !rateLimitKey.isEmpty()){
            LazyBucket bucket = userBuckets.computeIfAbsent(rateLimitKey, k -> new LazyBucket(bucketCapacity));
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
        try{
            if(!scheduler.awaitTermination(5, TimeUnit.SECONDS)){
                scheduler.shutdownNow();
            }
        }catch(InterruptedException e){
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
