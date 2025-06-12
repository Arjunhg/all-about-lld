
// class Animal {
//     void sound(){
//         System.out.println("Animal makes a sound");
//     }
// }

// class Dog extends Animal {
//     @Override
//     void sound() {
//         System.out.println("Dog barks");
//     }
// }

// class Cat extends Animal {
//     @Override
//     void sound() {
//         System.out.println("Cat meows");
//     }
// }

// //Not allowed in Java due to multiple inheritance issues. 
// public class Bg_D_MultipleInheritance extends Dog, Cat {
//     public static void main(String[] args) {
//         Bg_D_MultipleInheritance hybrid = new Bg_D_MultipleInheritance();
//         hybrid.sound(); // This will cause a compile-time error due to multiple inheritance. Ambiguity arises here. Should it call Dog's or Cat's sound method?
//     }
// }


// /* Hierarchical Multiple Inheritance Example
// class A {
//     void show() {}
// }
// class B extends A {
//     void show() {}
// }
// class C extends A {
//     void show() {}
// }
// class D extends B, C {  // Now which show() to use?
// }
// // This will cause a compile-time error due to multiple inheritance.
//  */

// /*MultiLevel Multiple Inheritance Example
//  class A {
//     void show() {}
// }

// class B extends A {
//     void see() {}
// }

// class C extends B {
//     void talk() {}
// }

// class D extends B, C {  // ❌ Java still does not allow this
// }

// When D calls a method, Java doesn't know whether to:

// Use B's version of inherited methods
// Use C's version of inherited methods
// Handle conflicting method signatures

// */