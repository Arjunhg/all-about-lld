package J_LLD_Concurrency_Problems.C_Design_Rate_Limiter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.CommonEnums.RateLimiterType;
import J_LLD_Concurrency_Problems.C_Design_Rate_Limiter.Controller.RateLimiterController;

public class Main {
    public static void main(String[] args) {
        demonstrateRateLimiting();
    }

    private static void demonstrateRateLimiting(){
        Map<String, Object> config = new HashMap<>();
        config.put("capacity", 5);
        config.put("refreshRate", 1);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        RateLimiterController controller = new RateLimiterController(RateLimiterType.TOKEN_BUCKET, config, executor);

        // Global rate limit => Burst of request
        System.out.println("---- Global Rate Limiting ----");
        sendBurstRequest(controller, 10, null);

        // Global rate limit => Waiting for tokens to refill
        System.out.println("---- Global Rate Limiting with Wait ----");
        System.out.println("Waiting for 6 seconds to allow token refill...");
        sleep(6000);
        sendBurstRequest(controller, 10, null);

        // User rate limit => Burst of request
        System.out.println("---- User Rate Limiting ----");
        String[] users = {"user1", "user2", "user3"};
        for(String user : users){
            System.out.println("User: " + user);
            sendBurstRequest(controller, 7, user);
        }

    }

    private static void sendBurstRequest(RateLimiterController controller, int count, String rateLimitKey){
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        for(int i=0; i<=count; i++){
            // same rate limit key used -> share the same bucket
            futures.add(controller.processRequest(rateLimitKey));
        }

        // Wait for all requests to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        // count how many allowed
        long allowed = futures.stream().filter(CompletableFuture::join).count(); // join to get the result (Boolean)
        System.out.printf("Results: %d allowed, %d blocked (total: %d)%n", allowed, count - allowed, count);
    }

    private static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
