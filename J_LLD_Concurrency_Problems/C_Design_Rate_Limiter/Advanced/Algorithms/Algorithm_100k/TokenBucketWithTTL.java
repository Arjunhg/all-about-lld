package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Algorithms.Algorithm_100k;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;

/*
* Token Bucket with Time-To-Live (TTL) Rate Limiter
* Sutitable for systems with < 100K users

* Features:
* - Lazy refill for user buckets
* - Periodic cleanup of inactive user buckets based on TTL
* - Eager refill for global bucket
*/

public class TokenBucketWithTTL implements RateLimiterInterface{
    private volatile int refreshRate;
    private final int bucketCapacity;
    private final long userBucketTTLMs; // Time-To-Live for user buckets in milliseconds

    private final Bucket globalBucket;
    private final Map<String, LazyBucket> userBuckets;

    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMs;
    private final long cleanupIntervalMs;

    private class Bucket {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int tokens){
            this.tokens = tokens;
        }

        private boolean tryConsume(){
            lock.lock();
            try{
                if(tokens > 0){
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

    private class LazyBucket {
        private int tokens;
        private long lastAccessTimestamp;
        private final ReentrantLock lock = new ReentrantLock();

        public LazyBucket(int tokens){
            this.tokens = tokens;
            this.lastAccessTimestamp = System.currentTimeMillis();
        }

        private boolean tryConsume(){
            lock.lock();
            try{
                refillIfNeeded();
                if(tokens > 0){
                    tokens--;
                    return true;
                }
                return false;
            }finally{
                lock.unlock();
            }
        }
        private void refillIfNeeded(){
            long now = System.currentTimeMillis();
            long elapsedSeconds = (now - lastAccessTimestamp) / 1000;
            if(elapsedSeconds > 0){
                int tokensToAdd = (int)(elapsedSeconds * refreshRate);
                tokens = Math.min(bucketCapacity, tokens + tokensToAdd);
                lastAccessTimestamp = now;
            }
        }

        private long getLastAccessTimestamp(){
            lock.lock();
            try{
                return lastAccessTimestamp;
            }finally{
                lock.unlock();
            }
        }
    }

    /**
     * @param bucketCapacity Maximum tokens in the bucket
     * @param refreshRate Tokens added per second
     * @param userBucketTTLMs Time-To-Live for user buckets in milliseconds
     */
    public TokenBucketWithTTL(int bucketCapacity, int refreshRate, long userBucketTTLMs){
        this.refreshRate = refreshRate;
        this.bucketCapacity = bucketCapacity;
        this.userBucketTTLMs = userBucketTTLMs;
        this.globalBucket = new Bucket(bucketCapacity);
        this.userBuckets = new ConcurrentHashMap<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.refillIntervalMs = 1000;
        this.cleanupIntervalMs = userBucketTTLMs;

        startRefillTask();
        startCleanupTask();
    }

    // Contructor with default 1 hour ttl
    public TokenBucketWithTTL(int bucketCapacity, int refreshRate){
        this(bucketCapacity, refreshRate, 3600_000);
    }

    // Refill only global bucket
    private void startRefillTask(){
        scheduler.scheduleAtFixedRate(() -> {
            globalBucket.refill();
        }, refillIntervalMs, refillIntervalMs, TimeUnit.MILLISECONDS);
    }

    // Periodically cleanup inactive user from user buckets
    private void startCleanupTask(){
        scheduler.scheduleAtFixedRate(() -> {
            // We don't want to check whether 1PM > 3PM (doesn't make sense)
            // Instead we check whether (3PM - 1PM) > TTL
            long currentTime = System.currentTimeMillis();
            int removedCount = 0;

            userBuckets.forEach((key, bucket) -> {
                if(currentTime - bucket.getLastAccessTimestamp() > userBucketTTLMs){
                    userBuckets.remove(key, bucket);
                }
            });

            if(removedCount > 0){
                System.out.printf("[TTL Cleanup] Removed %d inactive user buckets. Remaning: %d%n", removedCount, userBuckets.size());
            }
        }, cleanupIntervalMs, cleanupIntervalMs, TimeUnit.MILLISECONDS);
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