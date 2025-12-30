package J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;

public interface WritePolicy<K, V> {
    // Write key-value pair to both cache and DB storage concurrent;y
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception;
}
