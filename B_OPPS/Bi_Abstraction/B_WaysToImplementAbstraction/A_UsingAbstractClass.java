package B_OPPS.Bi_Abstraction.B_WaysToImplementAbstraction;
// package Bi_B_WaysToImplementAbstraction;


// abstract class Animal {

//     // Increased security
//     private String secret = "Animal Secret";
//     protected String getSecret(){
//         return secret;
//     }
    
//     // For unique behavior, we use abstract methods
//     abstract void makeSound();

//     // For common behavior, we can use concrete methods
//     void sleep() {
//         System.out.println("Animal is sleeping");
//     }
// }


// // Better code resusability and maintainability
// class Dog extends Animal {
//     @Override
//     void makeSound(){
//         System.out.println("Bark");
//     }
// }

// class Cat extends Animal {
//     @Override
//     void makeSound(){
//         System.out.println("Meow");
//         System.out.println("Secret: " + getSecret()); 
//     }
// }

// public class Bi_B_A_UsingAbstractClass {
//     public static void main(String[] args) {
//         Animal dog = new Dog();
//         dog.makeSound(); // Output: Bark
//         dog.sleep(); // Output: Animal is sleeping

//         Animal cat = new Cat();
//         cat.makeSound(); // Output: Meow
//         cat.sleep(); // Output: Animal is sleeping
//     }
// }