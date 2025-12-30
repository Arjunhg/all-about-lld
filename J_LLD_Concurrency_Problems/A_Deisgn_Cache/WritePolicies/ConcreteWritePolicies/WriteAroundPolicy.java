package J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.ConcreteWritePolicies;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;

public class WriteAroundPolicy<K, V> implements WritePolicy<K, V> {
    
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception {
        // Just write to DB synchronously
        dbStorage.write(key, value);
        // No need for CompletableFuture here - single synchronous operation
    }
}