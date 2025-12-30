package J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.ConcreteWritePolicies;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;

public class WriteThroughPolicy<K, V> implements WritePolicy<K, V> {
    
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception {
        //  Write to both concurrently and wait for both to complete
        CompletableFuture<Void> cachedFuture = CompletableFuture.runAsync(() -> {
            try{
                cacheStorage.put(key, value);
            }catch(Exception e){
                throw new CompletionException(e);
            }
        });

        CompletableFuture<Void> dbFuture = CompletableFuture.runAsync(() -> {
            try{
                dbStorage.write(key, value);
            }catch(Exception e){
                throw new CompletionException(e);
            }
        });

        CompletableFuture.allOf(cachedFuture, dbFuture).join();
    }
}
