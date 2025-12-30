package J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.ConcreteWritePolicies;

import java.util.concurrent.CompletableFuture;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;

public class WriteBehindPolicy<K, V> implements WritePolicy<K, V> {
    
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception {
        //  Write to cache immediately (sync)
        cacheStorage.put(key, value);

        // Write to DB async (fire and forget)
        CompletableFuture.runAsync(() -> {
            try{
                dbStorage.write(key, value);
            }catch(Exception e){
                // throw new CompletionException(e); // Since this is fire-and-forget, the exception is effectively ignored
            }
        });
    }
}