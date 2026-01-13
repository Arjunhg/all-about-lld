package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Algorithms.Algorithm_1M;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;

/**
 * Token Bucket implementation with LRU (Least Recently Used) cache for user buckets.
 * Suitable for systems with 100K - 1M users.
 * 
 * Features:
 * - Lazy refill for user buckets
 * - LRU eviction when cache size limit is reached
 * - Eager refill for global bucket
 * - Thread-safe LRU cache implementation
 */

public class TokenBucketWithLRU implements RateLimiterInterface{
    private volatile int refreshRate;
    private final int bucketCapacity;
    private final int maxUserBuckets; // Maximum number of user buckets in LRU cache (to keep in memory)

    private final Bucket globalBucket;
    private final LRUCache<String, LazyBucket> userBuckets;

    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMs;

    // Thread safe LRU implementation using LinkedHashMap
    private class LRUCache<K, V> extends LinkedHashMap<K, V>{
        private final int maxSize;
        private final ReentrantLock lock = new ReentrantLock();

        public LRUCache(int maxSize){
            super(16, 0.75f, true); // What is accessOrder: true -> order by access (get/put), false -> order by insertion
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
            boolean shouldRemove = size() > maxSize;
            if(shouldRemove){
                System.out.printf("[LRU Eviction] Removing bucket for key: %s (Cache size: %d)%n", eldest.getKey(), size());
            }
            return shouldRemove;
        }

        public V getOrCompute(K key, Function<K, V> mappingFunction){
            lock.lock();
            try{
                V value = get(key);
                if(value==null){
                    value = mappingFunction.apply(key);
                    put(key, value);
                }
                return value;
            }finally{
                lock.unlock();
            }
        }

        public int getSize(){
            lock.lock();
            try{
                return size();
            }finally{
                lock.unlock();
            }
        }
    }

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
    }

    /**
     * @param bucketCapacity Maximum tokens in bucket
     * @param refreshRate Tokens added per second
     * @param maxUserBuckets Maximum number of user buckets to keep in memory (default: 100,000)
     */
    public TokenBucketWithLRU(int bucketCapacity, int refreshRate, int maxUserBuckets){
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.maxUserBuckets = maxUserBuckets;
        this.refillIntervalMs = 1000;
        
        this.globalBucket = new Bucket(bucketCapacity);
        this.userBuckets = new LRUCache<>(maxUserBuckets);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        
        startRefillTask();
    }

    /**
     * Constructor with default max of 100K user buckets
     */
    public TokenBucketWithLRU(int bucketCapacity, int refreshRate){
        this(bucketCapacity, refreshRate, 100000);
    }

    // Refill only global bucket
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