package J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.ConcreteStorages.ConcreteDBStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces.DBStorage;

public class SimpleDBStorage<K, V> implements DBStorage<K, V> {

    private final Map<K, V> database = new ConcurrentHashMap<>();

    /*
    - final means the reference cannot be changed to point to another object.
    - It doesn't mean the object is immutable
    - So this is allowed:
        database.put(key, value);
    - This is not allowed:
        database = new ConcurrentHashMap<>(); // compile time error
    */
    
    @Override
    public void write(K key, V value) throws Exception{
        database.put(key, value);
    }

    @Override
    public V read(K key) throws Exception {
        if(!database.containsKey(key)){ 
            throw new Exception("Key not found in DBStorage: " + key);
        }
        return database.get(key);
    }

    @Override
    public void delete(K key) throws Exception {
        if(!database.containsKey(key)){ 
            throw new Exception("Key not found in DBStorage: " + key);
        }
        database.remove(key);
    }
}
