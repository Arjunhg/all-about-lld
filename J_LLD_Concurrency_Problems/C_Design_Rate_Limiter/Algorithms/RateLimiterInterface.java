package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Algorithms;

import java.util.Map;

public interface RateLimiterInterface {
    
    // rateLimitKey; if null, the key indicates global rate; if not null, it indicates per user rate limiting
    boolean giveAccess(String rateLimitKey);

    void updateConfiguration(Map<String, Object> config); // update any configuration of rate limiter without causing change in state of user's rate limiting

    void shutdown(); //shutdown any background tasks if any and rate limiter

}
