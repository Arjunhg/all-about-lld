package B_OPPS.G_Inheritance;

class Animal {
    void eat(){
        System.out.println("This animal eats food");
    }
}

class Dog extends Animal {
    void bark(){
        System.out.println("Dog barks");
    }
}

public class A_SingleInheritance {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.bark();
    }
}
