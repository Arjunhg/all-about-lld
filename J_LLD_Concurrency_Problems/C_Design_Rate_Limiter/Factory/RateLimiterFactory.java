package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.TokenBucketStrategy;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.CommonEnums.RateLimiterType;

public class RateLimiterFactory {

    private static final Map<RateLimiterType, Function<Map<String, Object> , RateLimiterInterface>> limiterFactory = new HashMap<>();
    
    static {
        // what does static block do?
        // It gets executed when the class is loaded in memory. So we can use it to register all rate limiter types with their implementations.

        limiterFactory.put(RateLimiterType.TOKEN_BUCKET, config -> {
            int capacity = (int) config.getOrDefault("capacity", 10);
            int refreshRate;
            if(config.containsKey("refreshRate")){
                refreshRate = (int) config.get("refreshRate"); // creates an entirely new configuration unlike updateConfiguration which updates existing configuration
            } else {
                double tokenPerSecond = (double) config.getOrDefault("tokenPerSecond", 10.0);
                refreshRate = (int) Math.round(tokenPerSecond);
            }
            return new TokenBucketStrategy(capacity, refreshRate);
        });
    }

    public static RateLimiterInterface createLimiter(RateLimiterType type, Map<String, Object> config){
        Function<Map<String, Object>, RateLimiterInterface> factory = limiterFactory.get(type);
        
        if(factory == null){
            throw new IllegalArgumentException("No rate limiter found for type: " + type);
        } else {
            return factory.apply(config);
        }
    }

    public static void registerLimiter(RateLimiterType type, Function<Map<String, Object>, RateLimiterInterface> factory){
        limiterFactory.put(type, factory);
    }
}
