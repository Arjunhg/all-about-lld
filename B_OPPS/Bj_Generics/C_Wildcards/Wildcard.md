# 🃏 Wildcards in Java Generics

## 📝 What are Wildcards?

Wildcards (`?`) represent **unknown types** in Java generics. They provide **flexibility** when you don't need to know the exact type, especially useful for method parameters where you're working with collections in a type-agnostic way.

## 🎯 Types of Wildcards

### 1️⃣ **Unbounded Wildcard (`?`)**
### 2️⃣ **Upper Bounded Wildcard (`? extends Type`)**
### 3️⃣ **Lower Bounded Wildcard (`? super Type`)**

---

## 1️⃣ Unbounded Wildcard (`?`)

### 📝 Definition
```java
List<?> list  // "A list of unknown type"
```

### ✨ Use Case
- When you **don't care about the specific type**
- Perfect for **read-only operations** that work with any type
- No type-specific logic needed

### 💡 Example
```java
static void printList(List<?> list) {
    for (Object item : list) {  // Can only treat items as Object
        System.out.println(item);
    }
}

// Works with any type
printList(Arrays.asList(1, 2, 3));           // List<Integer>
printList(Arrays.asList("A", "B", "C"));     // List<String>
printList(Arrays.asList(1.1, 2.2, 3.3));     // List<Double>
```

### ⚠️ Limitations
- Can only read items as `Object`
- Cannot add elements (except `null`)

---

## 2️⃣ Upper Bounded Wildcard (`? extends Type`)

### 📝 Definition
```java
List<? extends Number> list  // "A list of some unknown subtype of Number"
```

### 🎯 Meaning
- `?` represents a type that **extends** (is a subtype of) the specified type
- **Direction**: `? → Type → Object`
- The actual type is **below or equal to** `Type` in the hierarchy

### ✨ Use Cases
- **Reading** from collections when you need type safety
- **Producer** scenarios (collection produces items)
- When you need to access common methods of the upper bound

### 💡 Example
```java
static void printNumbers(List<? extends Number> list) {
    for (Number num : list) {  // Can treat items as Number
        System.out.println(num.doubleValue());  // Can call Number methods
    }
}

// Valid assignments
List<? extends Number> numbers;
numbers = new ArrayList<Integer>();  ✅ // Integer extends Number
numbers = new ArrayList<Double>();   ✅ // Double extends Number  
numbers = new ArrayList<Number>();   ✅ // Number extends Number (itself)
numbers = new ArrayList<Object>();   ❌ // Object doesn't extend Number
```

### 🔒 Read-Only Nature
```java
List<? extends Number> list = new ArrayList<Integer>();
Number num = list.get(0);  ✅ // Can read as Number
list.add(42);              ❌ // COMPILE ERROR - Cannot write!
```

### ❓ Why Can't You Add?
Even though the list contains `Integer` objects and you're adding an `Integer`, the compiler sees:
- `list` could be `List<Integer>`, `List<Double>`, or any `Number` subtype
- Adding `42` (Integer) to a potential `List<Double>` would break type safety
- **Java prevents this at compile-time**

---

## 3️⃣ Lower Bounded Wildcard (`? super Type`)

### 📝 Definition
```java
List<? super Integer> list  // "A list of some unknown supertype of Integer"
```

### 🎯 Meaning
- `?` represents a type that is a **supertype** of the specified type
- **Direction**: `Type → ?`
- The actual type is **above or equal to** `Type` in the hierarchy

### ✨ Use Cases
- **Writing** to collections when you need type safety
- **Consumer** scenarios (collection consumes items)
- When you want to add specific types safely

### 💡 Example
```java
static void addNumbers(List<? super Integer> list) {
    list.add(42);        ✅ // Can add Integer
    list.add(new Integer(100));  ✅ // Can add Integer
    // list.add(3.14);   ❌ // Cannot add Double
}

// Valid assignments
List<? super Integer> numbers;
numbers = new ArrayList<Integer>();  ✅ // Integer is supertype of Integer
numbers = new ArrayList<Number>();   ✅ // Number is supertype of Integer
numbers = new ArrayList<Object>();   ✅ // Object is supertype of Integer
numbers = new ArrayList<Double>();   ❌ // Double is not supertype of Integer
```

### 📖 Reading Limitations
```java
List<? super Integer> list = new ArrayList<Number>();
list.add(42);              ✅ // Can write Integer
Object obj = list.get(0);  ✅ // Can only read as Object safely
// Integer num = list.get(0);  ❌ // Cannot guarantee it's Integer
```

---

## 🆚 `?` vs `T` - When to Use Which?

| Aspect | `?` (Wildcard) | `<T>` (Type Parameter) |
|--------|----------------|------------------------|
| **Purpose** | Flexibility when type is irrelevant | Type safety with reusability |
| **Type Info** | Lost after declaration | Preserved throughout scope |
| **Return Type** | Cannot return specific type | Can return type `T` |
| **Multiple Usage** | Cannot reuse type | Can reuse `T` in method |

### 🔍 Example Comparison
```java
// Using Wildcard - Type info lost
static void processWildcard(List<?> list) {
    // Object item = list.get(0);  // Only Object available
    // return list.get(0);         // Cannot return unknown type
}

// Using Type Parameter - Type info preserved  
static <T> T processGeneric(List<T> list) {
    T item = list.get(0);    ✅ // Type preserved
    return item;             ✅ // Can return T
}
```

---

## 📋 PECS Rule (Producer Extends, Consumer Super)

### 🏭 **Producer Extends** (`? extends T`)
- Use when collection **produces/provides** items
- You **read** from the collection
- Collection is a **producer** of T

### 🍽️ **Consumer Super** (`? super T`)  
- Use when collection **consumes/accepts** items
- You **write** to the collection
- Collection is a **consumer** of T

### 💡 Memory Aid
```java
// PRODUCER (gives you items) → EXTENDS
List<? extends Number> producers = getNumbers();
Number item = producers.get(0);  // Collection produces Numbers

// CONSUMER (takes your items) → SUPER  
List<? super Integer> consumers = getContainer();
consumers.add(42);  // Collection consumes Integers
```

---

## ⚠️ Important Rules & Limitations

### 🔸 Upper Bounded (`? extends T`)
- ✅ **Can READ** items as type `T`
- ❌ **Cannot ADD** items (except `null`)
- ✅ **Can ASSIGN** subtypes to it

### 🔸 Lower Bounded (`? super T`)
- ✅ **Can ADD** items of type `T` or its subtypes
- ❌ **Can ONLY READ** as `Object` safely
- ✅ **Can ASSIGN** supertypes to it

### 🔸 Unbounded (`?`)
- ✅ **Can READ** items as `Object` only
- ❌ **Cannot ADD** items (except `null`)
- ✅ **Works with ANY** parameterized type

---

## 🎓 Advanced Examples

### 📊 Copy Method Implementation
```java
// Classic example using PECS
public static <T> void copy(List<? extends T> source, 
                           List<? super T> destination) {
    for (T item : source) {          // Read from producer
        destination.add(item);       // Write to consumer
    }
}

// Usage
List<Integer> integers = Arrays.asList(1, 2, 3);
List<Number> numbers = new ArrayList<>();
copy(integers, numbers);  ✅ // Integer extends Number, Number super Integer
```

### 🔄 Collections Utility Methods
```java
// Reading scenario - use extends
public static double sumNumbers(List<? extends Number> numbers) {
    double sum = 0.0;
    for (Number num : numbers) {
        sum += num.doubleValue();
    }
    return sum;
}

// Writing scenario - use super  
public static void addIntegers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}
```

---

## 🧠 Memory Tricks

### 🔹 **Hierarchy Direction**
- `extends` → **DOWN** the hierarchy (subtypes)
- `super` → **UP** the hierarchy (supertypes)

### 🔹 **Operation Permission**
- `extends` → **GET** out (read)
- `super` → **PUT** in (write)

### 🔹 **Assignment Rules**
```java
List<? extends Number> canAccept = new ArrayList<Integer>();  // ✅ Sub → Super
List<? super Integer> canAccept = new ArrayList<Number>();    // ✅ Super → Sub
```

---

## 🎯 Best Practices

### ✅ Do's
1. **Use PECS rule** for method parameters
2. **Use unbounded `?`** when type doesn't matter
3. **Prefer wildcards** over raw types
4. **Use bounded wildcards** for API flexibility

### ❌ Don'ts
1. **Don't use wildcards** in return types (use `T` instead)
2. **Don't mix** reading and writing with same wildcard
3. **Don't use wildcards** when you need the type information later

---

## 🎪 Real-World Interview Questions

### ❓ Question 1
```java
List<? extends Number> list = new ArrayList<Integer>();
list.add(42);  // What happens?
```
**Answer**: Compile error. Cannot add to `? extends` - it's read-only.

### ❓ Question 2  
```java
List<? super Integer> list = new ArrayList<Number>();
Integer val = list.get(0);  // What happens?
```
**Answer**: Compile error. Can only read as `Object` from `? super`.

### ❓ Question 3
**When would you use `List<?>` vs `List<Object>`?**

**Answer**: 
- `List<?>` accepts any parameterized list (`List<String>`, `List<Integer>`)
- `List<Object>` only accepts `List<Object>` specifically
- `List<?>` is more flexible for reading operations

---

## 📚 Summary

Wildcards provide **flexibility** in Java generics:
- **`?`** - Maximum flexibility, minimal type info
- **`? extends T`** - Safe reading with type bounds
- **`? super T`** - Safe writing with type bounds
- **PECS rule** - Producer Extends, Consumer Super

Remember: Wildcards are about **what you can do safely** with the collection, not about the actual runtime type!