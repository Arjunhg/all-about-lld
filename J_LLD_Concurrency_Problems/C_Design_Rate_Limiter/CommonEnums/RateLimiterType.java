package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.CommonEnums;

public enum RateLimiterType {
    TOKEN_BUCKET,
    FIXED_WINDOW,
    SLIDING_WINDOW_LOG,
    SLIDING_WINDOW_COUNTER,
    LEAKY_BUCKET
}
