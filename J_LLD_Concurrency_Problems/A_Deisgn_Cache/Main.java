package J_LLD_Concurrency_Problems.A_Deisgn_Cache;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.EvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.ConcreteEvictionAlgorithm.LRUEvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.MainCache.Cache;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.ConcreteStorages.ConcreteCacheStorage.InMemoryCacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.ConcreteStorages.ConcreteDBStorage.SimpleDBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.ConcreteWritePolicies.WriteThroughPolicy;

public class Main {
    public static void main(String[] args) {

        try {

            CacheStorage<String, String> cacheStorage = new InMemoryCacheStorage<>(5);
            DBStorage<String,String> dbStorage = new SimpleDBStorage<>();
            //Write concurrently on both storage
            WritePolicy<String,String> writePolicy = new WriteThroughPolicy<>();
            EvictionAlgorithm<String> evictionAlg = new LRUEvictionAlgorithm<>();

            int numExecutors = Runtime.getRuntime().availableProcessors();
            Cache<String, String> cache = new Cache<>(cacheStorage, dbStorage, writePolicy, evictionAlg, numExecutors);

            // Write operations
            cache.updateData("A", "Apple").join(); // If we don't use join, the main thread will exit before the updateData task completes.
            // If we use join, the main thread will wait for the updateData task to complete before exiting.
            cache.updateData("B", "Banana").join();
            cache.updateData("C", "Cherry").join();
            cache.updateData("D", "Date").join();
            cache.updateData("E", "Elderberry").join();

            // Now in memory cache is full. Next write should evict one key based on LRU.
            cache.updateData("F", "Friend").join();

            // Read operations
            try{
                String valueA = cache.accessData("A").join();
                System.out.println("Key: A, Value: " + valueA);
            }catch(Exception e){
                System.out.println("Key A not found in cache (expected if it was evicted)");
            }

            String valueF = cache.accessData("F").join();
            System.out.println("F: " + valueF);

            // Update existing key and then read to verify read-your-own-write consistency
            cache.updateData("B", "Blueberry").join();
            String valueB = cache.accessData("B").join();
            System.out.println("B: " + valueB);

            cache.shutDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
