## 1. Can Java Interface Methods Be Public, Abstract, Default, Static, Private, Private Static?

Yes, Java interface methods can have various modifiers, depending on their type and the Java version:

- **public**: All abstract, default, and static methods in an interface are implicitly public. You can explicitly add the `public` modifier, but it is not required.
- **abstract**: Abstract methods are the traditional type of interface method, without an implementation. They are implicitly public and abstract.
- **default**: Introduced in Java 8, default methods provide a default implementation within the interface itself. They are implicitly public.
- **static**: Also introduced in Java 8, static methods belong to the interface itself and can be called directly using the interface name. They are implicitly public.
- **private**: Introduced in Java 9, private methods can be used within an interface to encapsulate common code shared by default or static methods. They cannot be accessed by implementing classes or other interfaces.
- **private static**: Also introduced in Java 9, private static methods are similar to private methods but are static, meaning they belong to the interface and can be called by other static or default methods within the same interface.

**Example:**
```java
interface MyInterface {
    // Implicitly public and abstract
    void abstractMethod();

    // Implicitly public
    default void defaultMethod() {
        System.out.println("Default implementation");
    }

    // Implicitly public
    static void staticMethod() {
        System.out.println("Static method");
    }

    // Private to the interface
    private void privateMethod() {
        System.out.println("Private instance method");
    }

    // Private and static to the interface
    private static void privateStaticMethod() {
        System.out.println("Private static method");
    }
}
```

**References:**
- [Java 9 interface: why default modifier converted into public modifier](https://stackoverflow.com/questions/54553041/java-9-interface-why-default-modifier-converted-into-public-modifier)
- [Java Tutorials: Interface Definition](https://docs.oracle.com/javase/tutorial/java/IandI/interfaceDef.html)

---

## 2. Open/Closed Principle: Interface vs Abstract Class

You can implement the **Open/Closed Principle (OCP)** using either **interfaces** or **abstract classes** in Java. Both approaches help achieve polymorphism and make your code open for extension, closed for modification.

### When to Use Which?

| Aspect                                  | Interface         | Abstract Class          |
| ---------------------------------------- | ----------------- | ---------------------- |
| Multiple inheritance                    | ✅ Yes            | ❌ No                  |
| Default method implementation (Java 8+) | ✅ Yes (`default`)| ✅ Yes (normal methods)|
| Use for pure behavior contract           | ✅ Best suited    | ➖ Possible but overkill|
| Shared state/fields                      | ❌ Not allowed    | ✅ Allowed             |
| Common base logic/code reuse              | ❌ No             | ✅ Yes                 |

### Example: Payment Processor

- Use an **interface** if each payment type only has behavior (no shared data).
- Use an **abstract class** if you want to provide common methods or fields for all payment types (e.g., logging, validation, currency conversion).

> In either case, you can apply OCP by extending or implementing instead of modifying the existing code.

### Summary Table

| Feature           | Interface           | Abstract Class                   |
|-------------------|--------------------|----------------------------------|
| Primary Purpose   | Define contract    | Define contract & share logic    |
| Shared Logic      | No                 | Can have shared logic            |
| Inheritance       | Multiple           | Single                           |
| Methods           | Abstract/default   | Abstract and concrete            |
| Fields            | No (except constants)| Can have fields                |
| Use Case          | Unrelated classes  | Related classes with shared logic|

**In essence:**  
- Use interfaces for unrelated classes that share behavior.
- Use abstract classes for related classes that share logic.

---

## 3. What Is the Default Access Modifier in Java?

In Java, when no access modifier (like `public`, `private`, or `protected`) is provided for a method or a class (specifically, a top-level class), its access level defaults to **package-private**.

**Package-private** access means the class or member is accessible only within the same package. It is not accessible from classes in other packages, even if those classes are subclasses.

**Key Points:**
- **No Keyword:** There is no explicit keyword for package-private; it is the default when no modifier is used.
- **Accessibility:** Visible to any class within the same package.
- **Encapsulation:** Restricts access to internal implementation details within a package, allowing collaboration among classes in the package.
- **Interfaces:** For interface members, if no access modifier is specified, methods are implicitly `public abstract` and fields are `public static final`, not package-private.

