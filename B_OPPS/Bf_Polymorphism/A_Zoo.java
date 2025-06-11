package B_OPPS.Bf_Polymorphism;

// Superclass
class Animal {
    public void speak() {
        System.out.println("Animal makes a sound.");
    }
}

// Subclass 1
class Dog extends Animal {
    @Override // Indicates this method overrides a superclass method
    public void speak() {
        // super.speak(); If need to call the superclass method as wwll
        System.out.println("Woof!");
    }
}

// Subclass 2
class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("Meow!");
    }
}

// Usage:
public class A_Zoo {
    public static void main(String[] args) {
        Animal myDog = new Dog(); // Declared type is Animal, actual type is Dog
        Animal myCat = new Cat(); // Declared type is Animal, actual type is Cat
        Animal myAnimal = new Animal(); // Declared and actual type is Animal

        myDog.speak();    // Output: Woof! (calls Dog's speak method)
        myCat.speak();    // Output: Meow! (calls Cat's speak method)
        myAnimal.speak(); // Output: Animal makes a sound. (calls Animal's speak method)
    }
}
