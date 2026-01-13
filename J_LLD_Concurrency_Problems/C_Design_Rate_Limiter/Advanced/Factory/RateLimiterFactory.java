package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Algorithms.Algorithm_100k.TokenBucketWithTTL;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Algorithms.Algorithm_1M.TokenBucketWithLRU;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Algorithms.Algorithm_large.TokenBucketWithRedis;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Advanced.Enums.ScalingStrategy;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.TokenBucketStrategy;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.CommonEnums.RateLimiterType;

public class RateLimiterFactory {
    
    private static final Map<RateLimiterType, Function<Map<String, Object>, RateLimiterInterface>> limiterFactory = new HashMap<>();
    private static final Map<ScalingStrategy, Function<Map<String,Object>, RateLimiterInterface>> scalingFactory = new HashMap<>();


    static {
        limiterFactory.put(RateLimiterType.TOKEN_BUCKET, config -> {
            int capacity = (int)config.getOrDefault("capacity", 10);
            int refreshRate = (int)config.getOrDefault("refreshRate", 1);
            return new TokenBucketStrategy(capacity, refreshRate);
        });
    }

    static {
        scalingFactory.put(ScalingStrategy.IN_MEMORY_TTL, config -> 
            new TokenBucketWithTTL((int)config.getOrDefault("capacity", 10), getRefreshRate(config), (long)config.getOrDefault("userBucketTTLMs", 3600_000))
        );
        scalingFactory.put(ScalingStrategy.IN_MEMORY_LRU, config -> 
            new TokenBucketWithLRU((int)config.getOrDefault("capacity", 10), getRefreshRate(config), (int)config.getOrDefault("maxUserBuckets", 100_000))
        );
        scalingFactory.put(ScalingStrategy.DISTRIBUTED_REDIS, config -> 
            new TokenBucketWithRedis((int)config.getOrDefault("capacity", 10), getRefreshRate(config), (int)config.getOrDefault("userBucketTTLSeconds", 3600), null)
        );
    }

    public static RateLimiterInterface createLimiter(RateLimiterType type, Map<String, Object> config){
        Function<Map<String, Object>, RateLimiterInterface> factory = limiterFactory.get(type);
        if(factory==null){
            throw new IllegalArgumentException("Unsupported Rate Limiter Type: " + type);
        }
        return factory.apply(config);
    }

    public static RateLimiterInterface createScalingLimiter(ScalingStrategy strategy, Map<String, Object> config){
        Function<Map<String, Object>, RateLimiterInterface> factory = scalingFactory.get(strategy);
        if(factory==null){
            throw new IllegalArgumentException("Unsupported Scaling Strategy: " + strategy);
        }
        return factory.apply(config);
    }

    
    public static void registerLimiter(RateLimiterType type, Function<Map<String, Object>, RateLimiterInterface> factory) {
        limiterFactory.put(type, factory);
    }

    private static int getRefreshRate(Map<String, Object> config) {
        if (config.containsKey("refreshRate")) {
            return (int) config.get("refreshRate");
        } else {
            double tokenPerSecond = (double) config.getOrDefault("tokenPerSecond", 10.0);
            return (int) Math.round(tokenPerSecond);
        }
    }
}
