package J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.ConcreteStorages.ConcreteCacheStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.CacheStorage;

public class InMemoryCacheStorage<K, V> implements CacheStorage<K, V> {

    private final int capacity;
    private final Map<K, V> cache;

    public InMemoryCacheStorage(int capacity){
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
    }
    /* Why constructor based initialization here and not in DBStorage?
        - The cache has a bounded capacity (e.g., 100 entries). This is fundamental to caching - you need to know the limit upfront to implement eviction policies (LRU, LFU, etc.)
        - This follows the principle: inject dependencies/configuration that vary, hardcode what's constant.
        - If our DB storage later needs configuration (like connection string, storage path, etc.), we can similarly pass those via its constructor.
    
    */

    @Override
    public void put(K key, V value) throws Exception {
        cache.put(key, value);
    }

    @Override
    public V get(K key) throws Exception {
        if(!cache.containsKey(key)){
            throw new Exception("Key not found in cache");
        }
        return cache.get(key);
    }

    @Override
    public void remove(K key) throws Exception {
        if(!cache.containsKey(key)){
            throw new Exception("Key not found in cache");
        }
        cache.remove(key);
    }

    @Override
    public boolean containsKey(K key) throws Exception {
        return cache.containsKey(key);
    }

    @Override
    public int size() throws Exception {
        return cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}