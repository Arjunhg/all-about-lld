package B_OPPS.G_Inheritance;
class Animal {
    void eat(){
        System.out.println("This animal eats food");
    }
}

class Mammal extends Animal {
    void walk(){
        System.out.println("This mammal walks");
    }
}

class Dog extends Mammal {
    void bark(){
        System.out.println("Dog barks");
    }
}

public class B_MultilevelInheritance{
    public static void main(String[] args) {
        // Dog dog = new Dog();
        Dog dog = new Dog(); // Upcasting to Animal type
        dog.eat();
        dog.walk();
        dog.bark();
    }
}