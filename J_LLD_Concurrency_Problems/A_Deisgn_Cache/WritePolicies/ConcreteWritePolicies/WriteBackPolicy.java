package J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.ConcreteWritePolicies;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;

public class WriteBackPolicy<K, V> implements WritePolicy<K, V> {

    private final Queue<Map.Entry<K, V>> writeQueue = new ConcurrentLinkedQueue<>(); // When you delay DB writes, you must remember what needs to be written later. Each entyr: "At some point, write (key, value) to DB"

    
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception {
        // Write to cache immediately (sync)
        cacheStorage.put(key, value);
        writeQueue.offer(Map.entry(key, value));
        
        // Batch write asynchronously to DB
        if (writeQueue.size() >= 10) {
            CompletableFuture.runAsync(() -> flushBatch(dbStorage));
        }
    }

    //Take multiple pending writes from the queue and write them to the database in one go.
    private void flushBatch(DBStorage<K, V> dbStorage) {
        int batchSize = 10;

        for (int i = 0; i < batchSize; i++) {
            Map.Entry<K, V> entry = writeQueue.poll();
            if (entry == null) {
                break;
            }
            try {
                dbStorage.write(entry.getKey(), entry.getValue());
            } catch (Exception e) {
                // handle failure: retry, log, requeue, etc.
                e.printStackTrace();
            }
        }
    }

}
