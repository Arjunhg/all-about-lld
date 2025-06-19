package B_OPPS.Bi_Abstraction.B_WaysToImplementAbstraction;
// package Bi_B_WaysToImplementAbstraction;

// interface Animal {
//     void makeSound(); // Abstract method
//     void sleep(); // Abstract method
// }

// class Dog implements Animal {
//     @Override
//     public void makeSound() {
//         System.out.println("Bark");
//     }

//     @Override
//     public void sleep() {
//         System.out.println("Dog is sleeping");
//     }
// }

// class Cat implements Animal {
//     @Override
//     public void makeSound() {
//         System.out.println("Meow");
//     }

//     @Override
//     public void sleep() {
//         System.out.println("Cat is sleeping");
//     }
// }

// public class Bi_B_B_UsingInterface {
//     public static void main(String[] args) {
//         /*Works too but doesn't provide polymorphism 
//         Dog dog = new Dog();
//         dog.makeSound(); // Output: Bark
//         dog.sleep(); // Output: Dog is sleeping
//         */
        
//         Animal dog = new Dog();
//         dog.makeSound(); // Output: Bark
//         dog.sleep(); // Output: Dog is sleeping

//         Animal cat = new Cat();
//         cat.makeSound(); // Output: Meow
//         cat.sleep(); // Output: Cat is sleeping
        
//     }
// }
