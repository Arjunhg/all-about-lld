package J_LLD_Concurrency_Problems.A_Deisgn_Cache.MainCache;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.EvictionAlgorithms.EvictionAlgorithm;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.Executors.KeyBasedExecutor;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;
import J_LLD_Concurrency_Problems.A_Deisgn_Cache.WritePolicies.WritePolicy;

public class Cache<K, V> {
    
    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;
    private final KeyBasedExecutor keyBasedExecutor;

    public Cache(CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage, WritePolicy<K, V> writePolicy, EvictionAlgorithm<K> evictionAlgorithm, int numExecutors) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
        this.keyBasedExecutor = new KeyBasedExecutor(numExecutors);
    }

    //Read data from the cache and update the eviction algorithm as key is accessed
    public CompletableFuture<V> accessData(K key){
        return keyBasedExecutor.submitTask(key, () -> { // key, supplier. key is mapped to exactly one executor. That executor has ONE thread
            // in submitTask the task is queued instead of sequentially executing it.
            /* Is we do this for same executor, same thread:
                accessData(key);
                accessData(key);
                accessData(key);

            - Execution looks like: task1 → task2 → task3
            - No parellel execution for same key
            - This ensures sequential execution for a key, not for across the key (that's why we use multiple executors)
             */
            try{
                if(!cacheStorage.containsKey(key)){
                    throw new Exception("Key not found in cache");
                }
                evictionAlgorithm.keyAccessed(key);
                return cacheStorage.get(key);
            }catch(Exception e){
                // e.printStackTrace(); // Gives error because supplier must return V
                throw new CompletionException(e); 
            }
        });
    }

    // Write data to both cache and DB using write through policy. If the key is new and cache is full, evict a key using the eviction algorithm.
    public CompletableFuture<Void> updateData(K key, V value){
        return keyBasedExecutor.submitTask(key, () -> {
            try{
                if(cacheStorage.containsKey(key)){
                    // update existing key using concurrent writes
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }else{
                    // new key: check capacity and possibly evict
                    if(cacheStorage.size() >= cacheStorage.getCapacity()){
                        K evictedKey = evictionAlgorithm.evictKey();
                        if(evictedKey != null){
                            // remove evicted key executor to maintain order
                            int currIdx = keyBasedExecutor.getExecutorIndexForKey(key);
                            int evictedIdx = keyBasedExecutor.getExecutorIndexForKey(evictedKey);
                            if(currIdx == evictedIdx){
                                cacheStorage.remove(evictedKey);
                            }else{
                                CompletableFuture<Void> removalFuture = keyBasedExecutor.submitTask(evictedKey, () -> {
                                    try{
                                        cacheStorage.remove(evictedKey);
                                        return null;
                                    }catch(Exception e){
                                        throw new CompletionException(e);
                                    }
                                });
                                removalFuture.join();
                            }
                        }
                    }
                    // now write the new key
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }
                return null;
            } catch(Exception e){
                throw new CompletionException(e); 
            }
        });
    }

    public void shutDown(){
        keyBasedExecutor.shutDown();
    }
}
