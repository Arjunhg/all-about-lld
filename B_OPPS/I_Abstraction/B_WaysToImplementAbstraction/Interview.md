# 👩🏼‍🎓 Interview Questions: Abstract Classes & Interfaces

## 🚀 Core Concepts

### 1. What is the difference between an abstract class and an interface in Java? When would you use one over the other?

**✨ Answer:**

Abstract classes are used when classes share common functionality and state, whereas interfaces are used to define a contract for unrelated classes. Use abstract classes when you need shared code and interfaces for behavior enforcement.

```java
abstract class Animal {
    String name;
    
    Animal(String name) {
        this.name = name;
    }
    
    abstract void sound();
}

interface Pet {
    void play();
}

class Dog extends Animal implements Pet {
    Dog(String name) {
        super(name);
    }
    
    @Override
    void sound() {
        System.out.println(name + " barks.");
    }
    
    @Override
    public void play() {
        System.out.println(name + " plays fetch.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        dog.sound(); // Output: Buddy barks.
        dog.play();  // Output: Buddy plays fetch.
    }
}
```

---

### 2. Can an abstract class implement an interface? If yes, why would you do it?

**✨ Answer:**

Yes, an abstract class can implement an interface to provide partial implementation. This is useful when some methods in the interface have common logic that can be shared across subclasses.

```java
interface Pet {
    void play();
}

abstract class Animal implements Pet {
    String name;
    
    Animal(String name) {
        this.name = name;
    }
    
    abstract void sound();
    
    @Override
    public void play() {
        System.out.println(name + " plays.");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
    
    @Override
    void sound() {
        System.out.println(name + " barks.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        dog.sound(); // Output: Buddy barks.
        dog.play();  // Output: Buddy plays.
    }
}
```

---

### 3. Why can't we instantiate an abstract class? What would be the consequences if it were allowed?

**✨ Answer:**

Abstract classes are incomplete blueprints meant to be extended. Allowing instantiation would violate the principle of abstraction, as abstract methods lack implementation.

```java
abstract class Animal {
    abstract void sound();
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Animal animal = new Animal(); // Compile-time error
        Dog dog = new Dog();
        dog.sound(); // Output: Dog barks.
    }
}
```

---

## 🔍 Limitations & Comparisons

### 4. What are the limitations of using abstract classes over interfaces?

**✨ Answer:**

Abstract classes allow single inheritance only, whereas interfaces can be implemented by multiple classes, offering more flexibility.

**Abstract Class Example (Multiple Inheritance Error):**
```java
abstract class Animal {
    abstract void sound();
    
    void commonMethod() {
        System.out.println("Animal common method.");
    }
}

abstract class Mammal {
    abstract void eat();
    
    void commonMethod() {
        System.out.println("Mammal common method.");
    }
}

// This will cause a compile-time error
class Dog extends Animal, Mammal { // ❌ Error: Multiple inheritance not allowed
    @Override
    void sound() {
        System.out.println("Dog barks.");
    }
    
    @Override
    void eat() {
        System.out.println("Dog eats.");
    }
    
    @Override
    void commonMethod() {
        // Which commonMethod() to call? This causes ambiguity.
    }
}
```

**Interface Example (No Error):**
```java
interface Animal {
    void sound();
    
    default void commonMethod() {
        System.out.println("Animal common method.");
    }
}

interface Pet {
    void play();
    
    default void commonMethod() {
        System.out.println("Pet common method.");
    }
}

class Dog implements Animal, Pet {
    @Override
    public void sound() {
        System.out.println("Dog barks.");
    }
    
    @Override
    public void play() {
        System.out.println("Dog plays fetch.");
    }
    
    @Override
    public void commonMethod() {
        Animal.super.commonMethod();
        Pet.super.commonMethod();
        System.out.println("Dog's own common method.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound(); // Output: Dog barks.
        dog.play();  // Output: Dog plays fetch.
        dog.commonMethod(); 
        // Output: Animal common method.
        //         Pet common method.
        //         Dog's own common method.
    }
}
```

---

### 5. When should you not use an interface? Provide a practical example.

**✨ Answer:**

Avoid interfaces when the implementing classes share common functionality or state. For example, if Dog and Cat both need an `eat()` method with shared logic, an abstract class like Animal is more appropriate than an interface.

```java
abstract class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks.");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Cat meows.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        
        dog.eat();   // Output: Eating...
        dog.sound(); // Output: Dog barks.
        cat.eat();   // Output: Eating...
        cat.sound(); // Output: Cat meows.
    }
}
```

---

## 🎯 Default Methods in Interfaces

### 6. What are default methods in Java interfaces? Why were they introduced?

**✨ Answer:**

Default methods are methods in interfaces that have a body (implementation). They were introduced in Java 8 to provide backward compatibility. This allows interfaces to evolve by adding new methods without breaking existing implementations of the interface.

```java
interface Animal {
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}

class Dog implements Animal {
    // No need to override sound
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound(); // Output: This is a default animal sound.
    }
}
```

---

### 7. What is the difference between abstract methods and default methods in an interface?

**✨ Answer:**

Abstract methods have no body and must be implemented by a class that implements the interface. Default methods have a body and can be optionally overridden by implementing classes.

```java
interface Animal {
    void eat(); // Abstract method
    
    default void sound() { // Default method
        System.out.println("This is a default animal sound.");
    }
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog is eating.");
    }
    // sound() method is inherited from interface
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();   // Output: Dog is eating.
        dog.sound(); // Output: This is a default animal sound.
    }
}
```

---

### 8. Why do we need default methods in Java? Couldn't we achieve the same with abstract classes?

**✨ Answer:**

Default methods allow interfaces to add new behavior without forcing all implementing classes to change. Abstract classes cannot achieve this because Java does not allow multiple inheritance of classes. Interfaces with default methods enable flexibility while avoiding the diamond problem.

```java
interface Animal {
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}

abstract class Mammal {
    abstract void eat();
}

class Dog extends Mammal implements Animal {
    @Override
    void eat() {
        System.out.println("Dog is eating.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();   // Output: Dog is eating.
        dog.sound(); // Output: This is a default animal sound.
    }
}
```

---

### 9. Can a class implement an interface without overriding its default methods?

**✨ Answer:**

Yes, a class can implement an interface without overriding its default methods. The default implementation will be inherited. However, the class can override the method if it needs custom behavior.

```java
interface Animal {
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}

class Dog implements Animal {
    // No need to override sound - inherits default implementation
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound(); // Output: This is a default animal sound.
    }
}
```

---

## 🔥 Advanced Default Method Scenarios

### 10. What happens if a class implements an interface with a default method and also inherits the same method from a superclass? Which one gets priority?

**✨ Answer:**

The method from the superclass takes priority over the default method in the interface. The class will inherit the superclass's method unless it explicitly overrides it.

```java
interface Animal {
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}

class Mammal {
    public void sound() {
        System.out.println("This is a mammal sound.");
    }
}

class Dog extends Mammal implements Animal {
    // No need to override sound - inherits from Mammal (superclass priority)
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound(); // Output: This is a mammal sound.
    }
}
```

---

### 11. What happens if a class implements two interfaces that have a default method with the same name?

**✨ Answer:**

If a class implements two interfaces with the same default method, it must override the method to resolve the ambiguity explicitly.

**🫎 Example Scenario: Animal Sounds**

Imagine two interfaces, `DogBehavior` and `CatBehavior`, both of which have a `makeSound()` default method. A class `AnimalProcessor` implements both interfaces and needs to resolve the conflict explicitly.

```java
interface DogBehavior {
    default void makeSound() {
        System.out.println("Dog barks.");
    }
}

interface CatBehavior {
    default void makeSound() {
        System.out.println("Cat meows.");
    }
}

class AnimalProcessor implements DogBehavior, CatBehavior {
    // Resolving the conflict by overriding the method
    @Override
    public void makeSound() {
        System.out.println("Resolving conflict between DogBehavior and CatBehavior:");
        
        // Call the default method from DogBehavior
        DogBehavior.super.makeSound();
        
        // Call the default method from CatBehavior
        CatBehavior.super.makeSound();
        
        // Adding custom behavior
        System.out.println("Custom behavior: AnimalProcessor decides which sound to make.");
    }
}

public class Main {
    public static void main(String[] args) {
        AnimalProcessor processor = new AnimalProcessor();
        processor.makeSound();
        // Output: Resolving conflict between DogBehavior and CatBehavior:
        //         Dog barks.
        //         Cat meows.
        //         Custom behavior: AnimalProcessor decides which sound to make.
    }
}
```

**Explanation:**
- **Default Methods Conflict:** Both `DogBehavior` and `CatBehavior` define a `makeSound()` default method. When `AnimalProcessor` implements both interfaces, the compiler cannot determine which version to use.
- **Conflict Resolution:** To resolve the conflict, `AnimalProcessor` explicitly overrides the `makeSound()` method and uses `InterfaceName.super.methodName()` to call the specific default method from each interface.
- **Custom Logic:** The `makeSound()` method in `AnimalProcessor` adds custom behavior after calling the default methods from the interfaces.

---

### 12. Is it possible to override a default method and make it abstract in a subclass or interface? Why or why not?

**✨ Answer:**

No, a default method cannot be overridden and made abstract. Once a default method is defined, overriding implementations must provide a concrete implementation.

```java
interface Animal {
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks.");
    }
    // Cannot make this abstract - must provide concrete implementation
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound(); // Output: Dog barks.
    }
}
```

---

### 13. Can default methods access instance variables of the implementing class? Why or why not?

**✨ Answer:**

No, default methods cannot access instance variables of the implementing class because interfaces do not have state. Default methods are stateless and only work with parameters and their internal logic.

```java
interface Animal {
    default void sound() {
        // Cannot access instance variables from implementing class
        System.out.println("This is a default animal sound.");
    }
}

class Dog implements Animal {
    private String name = "Buddy";
    
    public void printName() {
        System.out.println("Dog's name is " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.printName(); // Output: Dog's name is Buddy
        dog.sound();     // Output: This is a default animal sound.
    }
}
```

---

### 14. What are some limitations of default methods in comparison to methods in abstract classes?

**✨ Answer:**

- Default methods cannot have instance variables.
- They cannot use `super` to refer to the implementing class's parent.
- Abstract classes can have constructors and fields, but interfaces cannot.

```java
// Abstract class example showcasing fields, constructors, and abstract methods
abstract class Animal {
    String name; // Instance variable
    
    // Constructor to initialize the name
    Animal(String name) {
        this.name = name;
    }
    
    // Abstract method
    abstract void sound();
    
    // Non-abstract method to demonstrate additional functionality
    void eat() {
        System.out.println(name + " is eating.");
    }
}

// Interface example showcasing default methods and their limitations
interface Playable {
    // Default method
    default void play() {
        System.out.println("Playing with the animal.");
    }
    
    // Interfaces can only contain static final variables (constants)
    String DEFAULT_NAME = "Unknown"; // This is static final by default
    
    /* 
    // ❌ This would cause an error - interfaces cannot have instance variables
    default void setName(String name) {
        this.name = name; // Error: Interfaces cannot have instance variables
    }
    */
}

// Dog class extends abstract class Animal and implements interface Playable
class Dog extends Animal implements Playable {
    // Constructor calling the abstract class constructor
    Dog(String name) {
        super(name);
    }
    
    // Overriding the abstract method
    @Override
    void sound() {
        System.out.println(name + " barks.");
    }
    
    /* 
    // ❌ This would cause an error - default methods cannot use super 
    // to refer to parent methods in an interface
    @Override
    public void play() {
        super.play(); // Error: Cannot use super to refer to a parent method in an interface
    }
    */
}

public class Main {
    public static void main(String[] args) {
        // Abstract class functionality
        Dog dog = new Dog("Buddy");
        dog.sound(); // Output: Buddy barks.
        dog.eat();   // Output: Buddy is eating.
        
        // Interface functionality
        dog.play();  // Output: Playing with the animal.
    }
}
```

---

## 📋 Summary

This comprehensive interview guide covers:
- **Core differences** between abstract classes and interfaces
- **Implementation strategies** and when to use each
- **Default methods** and their advanced usage scenarios
- **Conflict resolution** in multiple interface implementation
- **Limitations and best practices** for both approaches

Each question includes practical code examples to demonstrate the concepts in action! 🚀