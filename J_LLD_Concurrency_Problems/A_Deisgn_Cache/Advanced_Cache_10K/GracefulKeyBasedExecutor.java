package J_LLD_Concurrency_Problems.A_Deisgn_Cache.Advanced_Cache_10K;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Improved KeyBasedExecutor with graceful shutdown support.
 * 
 * Key improvements:
 * 1. awaitTermination after shutdown to allow in-flight tasks to complete
 * 2. Configurable timeout for graceful shutdown
 * 3. Logging of incomplete tasks on forced shutdown
 * 4. Thread naming for easier debugging
 */
public class GracefulKeyBasedExecutor {

    private final ExecutorService[] executors;
    private final int numExecutors;
    private final long shutdownTimeoutSeconds;

    public GracefulKeyBasedExecutor(int numExecutors) {
        this(numExecutors, 30); // Default 30 second shutdown timeout
    }

    public GracefulKeyBasedExecutor(int numExecutors, long shutdownTimeoutSeconds) {
        this.numExecutors = numExecutors;
        this.shutdownTimeoutSeconds = shutdownTimeoutSeconds;
        this.executors = new ExecutorService[numExecutors];

        for (int i = 0; i < numExecutors; i++) {
            final int executorId = i;
            executors[i] = Executors.newSingleThreadExecutor(r -> {
                Thread t = new Thread(r, "cache-executor-" + executorId);
                t.setDaemon(false); // Non-daemon to allow graceful shutdown
                return t;
            });
        }
    }

    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int idx = getExecutorIndexForKey(key);
        ExecutorService executor = executors[idx];
        return CompletableFuture.supplyAsync(task, executor);
    }

    public int getExecutorIndexForKey(Object key) {
        return Math.floorMod(key.hashCode(), numExecutors);
    }

    /**
     * Graceful shutdown with timeout.
     * 
     * 1. Stop accepting new tasks (shutdown)
     * 2. Wait for in-flight tasks to complete (awaitTermination)
     * 3. If timeout exceeded, force shutdown (shutdownNow)
     * 
     * @return true if all tasks completed gracefully, false if forced shutdown
     */
    public boolean shutDown() {
        // Phase 1: Signal all executors to stop accepting new tasks
        for (ExecutorService executor : executors) {
            executor.shutdown();
        }

        // Phase 2: Wait for all executors to complete
        boolean allTerminated = true;
        for (int i = 0; i < executors.length; i++) {
            try {
                if (!executors[i].awaitTermination(shutdownTimeoutSeconds, TimeUnit.SECONDS)) {
                    System.err.println("WARNING: Executor " + i + " did not terminate in " +
                            shutdownTimeoutSeconds + " seconds. Forcing shutdown.");
                    executors[i].shutdownNow();
                    allTerminated = false;
                }
            } catch (InterruptedException e) {
                System.err.println("WARNING: Shutdown interrupted for executor " + i);
                executors[i].shutdownNow();
                Thread.currentThread().interrupt();
                allTerminated = false;
            }
        }

        return allTerminated;
    }

    /**
     * Force immediate shutdown without waiting for tasks.
     * Use only when graceful shutdown is not possible.
     */
    public void shutDownNow() {
        for (ExecutorService executor : executors) {
            executor.shutdownNow();
        }
    }

    /**
     * Check if all executors have terminated.
     */
    public boolean isTerminated() {
        for (ExecutorService executor : executors) {
            if (!executor.isTerminated()) {
                return false;
            }
        }
        return true;
    }
}
