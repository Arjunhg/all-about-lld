# Polymorphic Factory Registration vs Abstract Factory Pattern

## What is Polymorphic Factory Registration?

The Polymorphic Factory Registration pattern is a variation of the Abstract Factory pattern that uses a central registry to manage and resolve factories dynamically at runtime.

### Example Implementation

```java
import java.util.*;

public class Main{
    public static void main(String[] args){
        FactoryRegistry.register("sedan", new SedanFactory());
        // FactoryRegistry.register("suv", new SUVFactory());
        
        Vehicle v = FactoryRegistry.create("sedan");
        v.drive();
    }
}

interface Vehicle {
    void drive();
}

interface VehicleFactory {
    Vehicle create();
}

class Sedan implements Vehicle {
    public void drive() { System.out.println("Driving a sedan"); }
}

class SedanFactory implements VehicleFactory {
    public Vehicle create() { return new Sedan(); }
}

class FactoryRegistry {
    private static final Map<String, VehicleFactory> registry = new HashMap<>();

    public static void register(String name, VehicleFactory factory) {
        registry.put(name.toUpperCase(), factory);
    }

    public static Vehicle create(String name) {
        return registry.get(name.toUpperCase()).create();
    }
}
```

## Understanding the Code Flow

### Step-by-Step Execution

1. **Registration Phase:**
   ```java
   FactoryRegistry.register("sedan", new SedanFactory());
   ```
   - Registers a `SedanFactory` instance against the key `"sedan"` in a central registry map

2. **Creation Phase:**
   ```java
   Vehicle v = FactoryRegistry.create("sedan");
   ```
   - Looks up the `"sedan"` key in the map
   - Gets the corresponding factory (`SedanFactory`)
   - Calls `.create()` on it
   - `SedanFactory.create()` returns a `Sedan` which implements `Vehicle`

### Flow Summary

1. Different `VehicleFactory` implementations are registered against string keys
2. When a `Vehicle` is needed, the registry is asked to "create" it using the correct factory
3. The `FactoryRegistry` does not know how to create `Vehicle` objects—it delegates this to the registered factories

## Design Pattern Classification

This pattern is a **Polymorphic Factory Registry**, which is a variation of the **Abstract Factory Pattern** with dynamic registration and resolution capabilities.

## Comparison with Factory and Abstract Factory Patterns

| Aspect                      | Factory Pattern                              | Abstract Factory Pattern                       | Polymorphic Factory Registry                            |
| --------------------------- | -------------------------------------------- | ---------------------------------------------- | ------------------------------------------------------- |
| **Object Creation Logic**   | Centralized, often with `switch/case`        | Decentralized, grouped by families (factories) | Decentralized, but registered at runtime                |
| **Extensibility**           | Harder (requires modifying switch-case)      | Easier, but usually hard-coded                 | Very easy — just register new factories dynamically     |
| **Factory Management**      | One factory class                            | Multiple factory classes                       | Central registry holds many factories                   |
| **Runtime Configurability** | No                                           | Rarely                                         | Yes — highly flexible                                   |
| **Example**                 | `ShapeFactory.create("circle")` using switch | `GUIFactory -> Button, Menu` families          | `FactoryRegistry.register("sedan", new SedanFactory())` |

## Advantages of Polymorphic Factory Registry

- **Open/Closed Principle friendly** — new factories can be added without changing existing code
- **Plugin-like extensibility** — great for systems that load factories via reflection, config files, or dependency injection
- **Loosely coupled** — the registry doesn't know anything about specific implementations, only the `VehicleFactory` interface

## Alternative: Using Switch-Case (Simple Factory)

If we used a `switch-case` approach instead, it would be more like a **Simple Factory** or **Factory Method** pattern:

```java
public static Vehicle create(String type) {
    switch(type.toLowerCase()) {
        case "sedan": return new Sedan();
        case "suv": return new SUV();
        default: throw new IllegalArgumentException();
    }
}
```

This approach violates the **Open/Closed Principle** since code modification is required to support new types.

## Terminology

This pattern can be referred to as:
- **Polymorphic Factory Registry Pattern**
- **Factory Registry** (a runtime extension of the Abstract Factory Pattern)

---

## Comparison: Polymorphic Factory Registry vs Normal Abstract Factory

### The Question: What are the differences and advantages?

Consider the normal Abstract Factory implementation:

```java
import java.util.*;

public class Main{
    public static void main(String[] args){
        VehicleFactory sedanFactory = new SedanFactory();
        Vehicle sedan = sedanFactory.create();
        sedan.drive();
    }
}

interface Vehicle {
    void drive();
}

interface VehicleFactory {
    Vehicle create();
}

class Sedan implements Vehicle {
    public void drive() { System.out.println("Driving a sedan"); }
}

class SedanFactory implements VehicleFactory {
    public Vehicle create() { return new Sedan(); }
}

class FactoryRegistry {
    private static final Map<String, VehicleFactory> registry = new HashMap<>();

    public static void register(String name, VehicleFactory factory) {
        registry.put(name.toUpperCase(), factory);
    }

    public static Vehicle create(String name) {
        return registry.get(name.toUpperCase()).create();
    }
}
```

Both approaches require:
- Adding a new factory class (e.g., `HondaFactory`)
- Adding a new product class (e.g., `Honda`)
- Similar number of lines in the main method

So why add the extra `FactoryRegistry` class?

## The Practical Differences

### 1. Normal Abstract Factory (Direct Instantiation)

```java
VehicleFactory sedanFactory = new SedanFactory();
Vehicle sedan = sedanFactory.create();
```

**Characteristics:**
- Manual instantiation of the factory (`new SedanFactory()`)
- Decision of which factory to use is hardcoded at compile time
- Good for static systems where available types are known at compile-time

### 2. Polymorphic Factory Registry (Dynamic Resolution)

```java
FactoryRegistry.register("sedan", new SedanFactory());
Vehicle sedan = FactoryRegistry.create("sedan");
```

**Characteristics:**
- Factories are registered dynamically at runtime
- Consumers don't need to know which factory class to instantiate—just pass a string key
- Great for plugin-based or extensible systems where:
  - Types may be loaded from config files, user input, reflection, etc.
  - Calling code should not depend on factory classes

## When to Use Factory Registry?

The registry approach might seem like overengineering if everything is hardcoded. However, consider these scenarios:

### Without Registry (Tightly Coupled)

```java
VehicleFactory factory;
if (userInput.equals("sedan")) factory = new SedanFactory();
else if (userInput.equals("suv")) factory = new SUVFactory();
// And so on...
```

This still requires conditional branching logic, which defeats the point of Abstract Factory's extensibility.

### With Registry (Loosely Coupled)

```java
// Load from config/user input/network
String userSelectedType = "suv";
Vehicle vehicle = FactoryRegistry.create(userSelectedType);
```

**Benefits:**
- No branching logic
- No dependency on specific classes like `SedanFactory`, `SUVFactory`
- Add new types by just registering them—no need to touch existing logic

## Summary of Tradeoffs

| Feature                              | Abstract Factory (Simple) | Factory Registry (Polymorphic)      |
| ------------------------------------ | ------------------------- | ----------------------------------- |
| **Instantiation**                    | Compile-time              | Runtime                             |
| **Extensibility**                    | Harder                    | Easier (no changes in usage code)   |
| **Coupling to Factory Classes**      | Tight                     | Loose                               |
| **Ideal for**                        | Static systems            | Dynamic, plugin-based architectures |
| **Reflection/config-driven support** | Awkward                   | Natural                             |
| **Lines of code**                    | Fewer (in small apps)     | Slightly more (but scalable)        |

## When to Prefer Each Approach

### Use Simple Abstract Factory When:
- All types are known at compile time
- Decoupling is not a primary concern
- Application is small and won't grow much

### Use Polymorphic Factory Registry When:
- Support for new types at runtime is needed
- Tight coupling should be reduced
- Application is extensible, plugin-based, or config-driven

## Key Insight

The `FactoryRegistry` adds complexity, and this only pays off when dynamic extensibility is needed.

If the system doesn't require runtime registration of factories or decoupling of object creation logic, stick with simple abstract factory.

However, if the system needs to grow, support plugins, load factory types dynamically, or support scripting, the Factory Registry approach becomes valuable.

---

## Use Case: Dynamic Input Handling

### When is Polymorphic Factory Registry Most Useful?

The registry pattern is particularly useful when the vehicle type comes from external input:

- User input (Scanner, CLI, GUI, REST API)
- Config files
- JSON/YAML data
- Database values
- Remote services

Instead of writing:

```java
if (input.equals("sedan")) new SedanFactory().create();
else if (input.equals("suv")) new SUVFactory().create();
// and so on...
```

You can simply do:

```java
Vehicle v = FactoryRegistry.create(input);
```

This is much cleaner and decoupled.

## Important Limitation

The registry approach doesn't remove the need for:
- Factory classes (`HondaFactory`, `SedanFactory`, etc.)
- Product classes (`Honda`, `Sedan`, etc.)

It removes the need to hardcode which one to use in the main logic.

## Responsibility Distribution

| Concern                       | Responsibility                                       |
| ----------------------------- | ---------------------------------------------------- |
| **What types are available?** | Developers register valid factories                  |
| **Which type to use?**        | Decided at runtime (e.g., user input)                |
| **How to create them?**       | Delegated to the correct factory class               |
| **Adding new type?**          | Just register a new factory, no code change in logic |

## Example: Plugin-Style Flow

Consider a large application like a vehicle rental platform:

```java
// Dynamic factory registration
for (Class<?> cls : findAllClassesImplementing(VehicleFactory.class)) {
    String name = cls.getSimpleName().replace("Factory", "").toLowerCase();
    VehicleFactory factory = (VehicleFactory) cls.getDeclaredConstructor().newInstance();
    FactoryRegistry.register(name, factory);
}
```

Now you can simply ask:

```java
Vehicle v = FactoryRegistry.create("honda");
```

Any developer who writes a `HondaFactory` and a `Honda` class gets their vehicle type supported without touching the rest of the codebase.

## Summary

The registry doesn't magically support unknown types, but it **decouples object creation from decision logic**:

- No more `if-else` or `switch` statements
- No tight coupling between input logic and factory logic
- Much easier to add/remove supported types dynamically

The Polymorphic Factory Registry pattern is best suited for scalable and plugin-style architectures where runtime flexibility is important.
