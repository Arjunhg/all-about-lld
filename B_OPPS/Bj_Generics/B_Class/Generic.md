# 🔧 Generic Classes in Java

## 📝 What are Generic Classes?

Generic classes are templates that allow you to write classes that work with different data types while maintaining type safety. They use **type parameters** (like `<T>`) to specify which types the class can work with.

## 🎯 Basic Syntax

```java
// Single type parameter
class ClassName<T> {
    T variable;
    
    public T getVariable() {
        return variable;
    }
}

// Multiple type parameters
class ClassName<T, U, V> {
    T first;
    U second;
    V third;
}
```

## 📋 Important Notes


### 🔸 Reference Types Only
```java
// ✅ Valid - Reference types
Test<Integer> intObj = new Test<>(10);
Test<String> strObj = new Test<>("Hello");
Test<Double> doubleObj = new Test<>(3.14);

// ❌ Invalid - Primitive types
// Test<int> invalidObj = new Test<>(10);    // Compile error
// Test<double> invalidObj = new Test<>(3.14); // Compile error
```

### 🔸 Type Erasure
- Generic type information is removed at runtime
- Bytecode contains raw types for backward compatibility
- Type checking happens at compile-time only

### 🔸 No Static Context with Type Parameters
```java
class Example<T> {
    // ❌ Cannot use T in static context
    // static T staticVar;           // Compile error
    // static T getStaticValue() {   // Compile error
    //     return null;
    // }
    
    // ✅ Can use specific types in static context
    static String staticVar = "Hello";
    static Integer getStaticValue() {
        return 42;
    }
}
```

## ✨ Benefits of Generic Classes

### 🎯 1. Type Safety
**Without Generics:**
```java
class Container {
    Object obj;
    
    public Object get() {
        return obj;
    }
}

// Usage - Error-prone
Container container = new Container();
container.obj = "Hello";
Integer value = (Integer) container.get(); // ❌ Runtime ClassCastException
```

**With Generics:**
```java
class Container<T> {
    T obj;
    
    public T get() {
        return obj;
    }
}

// Usage - Type-safe
Container<String> container = new Container<>();
container.obj = "Hello";
String value = container.get(); // ✅ No casting needed, compile-time safety
```

### 🔄 2. Code Reusability
```java
// One class works with multiple types
Test<Integer> numbers = new Test<>(42);
Test<String> texts = new Test<>("Hello");
Test<Double> decimals = new Test<>(3.14);
Test<Boolean> flags = new Test<>(true);
```

### 🚀 3. Performance
- No boxing/unboxing overhead with Object casting
- Compile-time optimization
- Eliminates runtime type checks

### 🛡️ 4. Compile-time Error Detection
```java
List<String> stringList = new ArrayList<>();
stringList.add("Hello");
// stringList.add(123); // ❌ Compile-time error - cannot add Integer to String list
```

## ⚠️ Things to Keep in Mind

### 🔸 1. Cannot Instantiate Type Parameters
```java
class Example<T> {
    public void method() {
        // ❌ Cannot instantiate T
        // T obj = new T(); // Compile error
    }
}
```

### 🔸 2. Cannot Create Arrays of Generic Types
```java
class Example<T> {
    // ❌ Cannot create array of generic type
    // T[] array = new T[10]; // Compile error
    
    // ✅ Alternative approaches
    Object[] array = new Object[10];
    List<T> list = new ArrayList<>();
}
```

### 🔸 3. Cannot Use instanceof with Parameterized Types
```java
List<String> stringList = new ArrayList<>();

// ❌ Cannot check parameterized type
// if (stringList instanceof List<String>) { } // Compile error

// ✅ Can check raw type
if (stringList instanceof List) { } // Works but unchecked warning
```

### 🔸 4. Generic Class Inheritance Rules
```java
// ✅ Valid inheritance patterns
class Parent<T> { }
class Child<T> extends Parent<T> { }        // Same type parameter
class Child<T, U> extends Parent<T> { }     // Additional type parameters
class Child extends Parent<String> { }      // Specific type

// ❌ Invalid patterns
// class Child<T> extends Parent<String> { } // Cannot override parent's type
```

## 🎓 Advanced Examples

### 📊 Bounded Type Parameters
```java
// T must extend Number
class NumberContainer<T extends Number> {
    T value;
    
    public double getDoubleValue() {
        return value.doubleValue(); // Can call Number methods
    }
}

// Usage
NumberContainer<Integer> intContainer = new NumberContainer<>();
NumberContainer<Double> doubleContainer = new NumberContainer<>();
// NumberContainer<String> stringContainer = new NumberContainer<>(); // ❌ Error
```

### 🔗 Wildcard Usage
```java
// Upper bounded wildcard
List<? extends Number> numbers = new ArrayList<Integer>();

// Lower bounded wildcard
List<? super Integer> integers = new ArrayList<Number>();

// Unbounded wildcard
List<?> unknownList = new ArrayList<String>();
```

## 🎯 Best Practices

### ✅ Do's
1. **Use meaningful type parameter names**
   ```java
   class Dictionary<K, V> { } // Better than Dictionary<T, U>
   ```

2. **Prefer generic types over raw types**
   ```java
   List<String> list = new ArrayList<>(); // ✅ Good
   List list = new ArrayList();           // ❌ Avoid raw types
   ```

3. **Use bounded type parameters when appropriate**
   ```java
   class Sorter<T extends Comparable<T>> { }
   ```

### ❌ Don'ts
1. **Don't use raw types in new code**
2. **Don't ignore unchecked warnings**
3. **Don't create generic exceptions**
   ```java
   // ❌ Generic exceptions not allowed
   // class MyException<T> extends Exception { }
   ```

## 🎪 Real-World Examples

### 📦 Container Classes
```java
class Box<T> {
    private T content;
    
    public void pack(T item) { this.content = item; }
    public T unpack() { return content; }
    public boolean isEmpty() { return content == null; }
}
```

### 🔗 Pair/Tuple Classes
```java
class Pair<T, U> {
    private final T first;
    private final U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() { return first; }
    public U getSecond() { return second; }
}
```

### 📈 Result Classes
```java
class Result<T, E> {
    private final T data;
    private final E error;
    private final boolean success;
    
    private Result(T data, E error, boolean success) {
        this.data = data;
        this.error = error;
        this.success = success;
    }
    
    public static <T, E> Result<T, E> success(T data) {
        return new Result<>(data, null, true);
    }
    
    public static <T, E> Result<T, E> failure(E error) {
        return new Result<>(null, error, false);
    }
}
```

---

## 📚 Summary

Generic classes provide:
- **Type Safety** at compile-time
- **Code Reusability** across different types
- **Performance Benefits** through elimination of casting
- **Better APIs** with clear type contracts

Remember: Generics are primarily a compile-time feature designed to catch type errors early and make code more readable and maintainable!