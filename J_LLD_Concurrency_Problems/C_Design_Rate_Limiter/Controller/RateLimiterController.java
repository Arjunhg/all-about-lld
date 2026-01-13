package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms.RateLimiterInterface;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.CommonEnums.RateLimiterType;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Factory.RateLimiterFactory;

public class RateLimiterController {
    
    private final RateLimiterInterface rateLimiter;
    private final ExecutorService executor;

    public RateLimiterController(RateLimiterType type, Map<String, Object> config, ExecutorService executorService){
        this.rateLimiter = RateLimiterFactory.createLimiter(type, config);
        this.executor = executorService;
    }

    public CompletableFuture<Boolean> processRequest(String rateLimitKey){
        return CompletableFuture.supplyAsync(() -> {
            boolean allowed = rateLimiter.giveAccess(rateLimitKey);
            if(allowed){
                System.out.printf("Request with key [%s]: Allowed%n", rateLimitKey);
            }else{
                System.out.printf("Request with key [%s]: Blocked%n", rateLimitKey);
            }
            return allowed;
        }, executor);
    }

    public void updateConfiguration(Map<String, Object> config){
        rateLimiter.updateConfiguration(config);
    }

    public void shutdown(){
        rateLimiter.shutdown();
        executor.shutdown();
    }
}
