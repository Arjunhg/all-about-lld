package B_OPPS.Bg_Inheritance;


//This type of inheritance can only be implemented using interfaces alongside class inheritance.

class Animal {
    void eat(){
        System.out.println("Animal eats");
    }
}

interface Mammal {
    void walk();
}
interface Pet {
    void play();
}

// Hybrid inheritance: Using class and interface combination
class Dog extends Animal implements Mammal, Pet {
    @Override
    void eat(){
        System.out.println("Dog eats food");
    }

    /*
     * 🔑 Visibility Rules When Implementing Interfaces:
     * 
     * 🏷️ In interfaces, methods are implicitly public abstract — even if you don't write anything.
     * ✅ While overriding them the method should be public so as to not reduce the visibility of methods.
     * 📦 In classes, methods are not public by default — they are package-private by default (i.e., no modifier means it's only accessible in the same package).
    */
    @Override
    public void walk(){
        System.out.println("The dog walks");
    }
    @Override
    public void play(){
        System.out.println("The dog plays with a ball");
    }
}

public class F_HydridInheritance {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Outputs: Dog eats food
        dog.walk(); // Outputs: The dog walks
        dog.play(); // Outputs: The dog plays with a ball
    }
}
