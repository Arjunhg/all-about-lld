package J_LLD_Concurrency_Problems.A_Deisgn_Cache.StorageMechanisms.Interfaces;

public interface DBStorage<K, V> {
    void write(K key, V value) throws Exception;
    V read(K key) throws Exception;
    void delete(K key) throws Exception;
}

/* Generics provides intent. We can use Object too but them storage maps something to something. (Would require casting and is prone to errors)

public interface DBStorage {
    void write(Object key, Object value) throws Exception;
    Object read(Object key) throws Exception;
    void delete(Object key) throws Exception;
}

DBStorage cache = ...
cache.write("user1", 42);

// You must cast
Integer value = (Integer) cache.read("user1");

cache.write("user1", 42);
cache.write(123, "oops"); // compiles fine, logic error

*/