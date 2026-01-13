package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Algorithms.Algorithm_large;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;

/**
 * Token Bucket implementation with Redis for distributed rate limiting.
 * Suitable for systems with > 1M users or distributed deployments.
 * 
 * Features:
 * - Redis-based distributed storage for user buckets
 * - Lua scripts for atomic token consumption (When you run a Lua script in Redis, the entire script executes as a single atomic operation.)
 * - In-memory global bucket (single instance)
 * - Lazy refill calculated in Lua script
 * 
 * Note: This is just a template implementation. You'll need to add actual Redis client
 * dependency (e.g., Jedis or Lettuce) to your project.
 */

public class TokenBucketWithRedis implements RateLimiterInterface {

    private final int bucketCapacity;
    private volatile int refreshRate;
    private final int userBucketTTLSeconds; // Time to live for user buckets in Redis

    private final Bucket globalBucket;
    private final RedisClient redisClient;

    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMs;

    // Lua script for aromic consumption with lazy refill
    private static final String LUA_CONSUME_TOKEN = 
        "local key = KEYS[1]\n" + 
        "local capacity = tonumber(ARGV[1])\n" +
        "local refill_rate = tonumber(ARGV[2]\n" +
        "local ttl = tonumber(ARGV[3])\n" +
        "local current_time = tonumber(ARGV[4])\n" +
        "\n" +
        "-- Get current state or initialize\n" +
        "local bucket = redic.call('HMGET', key, 'tokens', 'last_refill')\n" +
        "local tokens = tonumber(bucket[1]\n" +
        "local last_refill = tonumber(bucket[2]\n" +
        "\n" +
        "if not tokens then\n" +
        "   tokens = capacity\n" +
        "   last_refill = current_time" +
        "end\n" +
        "\n" +
        "-- Calculate lazy refill\n" +
        "local elapsed = (current_time - last_refill) / 1000.0\n" +
        "if elapsed > 0 then\n" +
        "   local tokens_to_add = math.floor(elapsed_time*refill_rate)\n" +
        "   tokens = math.min(capacity, tokens + tokens_to_add)\n" +
        "   last_refill = current_time\n" +
        "end\n" +
        "\n" +
        "-- Consume token if available\n" +
        "if tokens > 0 then\n" +
        "   tokens = tokens - 1\n" +
        "   allowed = 1\n" +
        "end\n" +
        "\n" +
        "-- Save time with TTL" +
        "redis.call('HMSET', key, 'tokens', tokens, 'last_refill', last_refill)\n" +
        "redis.call('EXPIRE', key, ttl)\n" +
        "\n" +
        "return allowed";
        
    /**
     * Global bucket with eager refill (single instance, not distributed)
     */
    private class Bucket {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int initialToken) {
            this.tokens = initialToken;
        }

        public boolean tryConsume() {
            lock.lock();
            try {
                if (tokens > 0) {
                    tokens--;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }

        public void refill() {
            lock.lock();
            try {
                tokens = Math.min(bucketCapacity, tokens + refreshRate);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Mock Redis client interface - replace with actual Redis client (Jedis/Lettuce)
     */
    private interface RedisClient {
        /**
         * Execute Lua script atomically
         * @return 1 if token consumed, 0 if rate limited
         */
        long evalScript(String script, String[] keys, String[] args);
        
        void close();
    }

    /**
     * Mock implementation - replace with actual Redis client
     */
    private static class MockRedisClient implements RedisClient {
        @Override
        public long evalScript(String script, String[] keys, String[] args) {
            // Replace with actual Redis client
            // Example with Jedis:
            // try (Jedis jedis = jedisPool.getResource()) {
            //     return (Long) jedis.eval(script, Arrays.asList(keys), Arrays.asList(args));
            // }
            throw new UnsupportedOperationException(
                "Redis client not configured. Add Jedis or Lettuce dependency and implement this method."
            );
        }

        @Override
        public void close() {
            // Close Redis connection pool
        }
    }

    public TokenBucketWithRedis(int bucketCapacity, int refreshRate, int userBucketTTLSeconds, RedisClient redisClient){
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.userBucketTTLSeconds = userBucketTTLSeconds;
        this.redisClient = redisClient;
        this.refillIntervalMs = 1000;
        
        this.globalBucket = new Bucket(bucketCapacity);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        
        startRefillTask();
    }

    public TokenBucketWithRedis(int bucketCapacity, int refreshRate) {
        this(bucketCapacity, refreshRate, 3600, new MockRedisClient());
    }

    /**
     * Refill only the global bucket
     */
    private void startRefillTask() {
        scheduler.scheduleAtFixedRate(() -> {
            globalBucket.refill();
        }, refillIntervalMs, refillIntervalMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean giveAccess(String rateLimitKey) {
        if (rateLimitKey != null && !rateLimitKey.isEmpty()) {
            // Distributed rate limiting via Redis
            String redisKey = "rate_limit:user:" + rateLimitKey;
            long currentTime = System.currentTimeMillis();
            
            String[] keys = {redisKey};
            String[] args = {
                String.valueOf(bucketCapacity),
                String.valueOf(refreshRate),
                String.valueOf(userBucketTTLSeconds),
                String.valueOf(currentTime)
            };
            
            try {
                long result = redisClient.evalScript(LUA_CONSUME_TOKEN, keys, args);
                return result == 1;
            } catch (Exception e) {
                System.err.printf("Redis error for key %s: %s%n", rateLimitKey, e.getMessage());
                // Fallback: allow request if Redis fails (fail-open policy)
                // Or you could fail-closed by returning false
                return true;
            }
        } else {
            // Global rate limiting (in-memory)
            return globalBucket.tryConsume();
        }
    }

    @Override
    public void updateConfiguration(Map<String, Object> config) {
        if (config.containsKey("refreshRate")) {
            this.refreshRate = (int) config.get("refreshRate");
        }
    }

    @Override
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        redisClient.close();
    }

    
}
