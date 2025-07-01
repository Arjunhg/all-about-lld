package B_OPPS.G_Inheritance;
// /*
//  * 💡 Java's Solution to Multiple Inheritance: INTERFACES
//  * 
//  * 🚫 Problem: Java doesn't allow extending multiple classes
//  * ✅ Solution: Use interfaces to achieve multiple inheritance
//  * 
//  * 🔑 How it works:
//  * - A class can implement multiple interfaces
//  * - Must provide implementation for all abstract interface methods
//  * - If an interface contains default or static methods (with body):
//  *   • Class is NOT required to implement them
//  *   • Can override default methods if needed
//  *   • Cannot override static methods (belong to interface)
//  * - No ambiguity because YOU decide what each method does
//  * - In Java, all interface methods without a body are implicitly:
//  *   • abstract and public
//  * 
//  * 🎯 Result: Multiple inheritance benefits without the confusion!
// */

interface Dog {
    default void sound(){
        System.out.println("Dog barks");
    }; 
}
interface Cat {
    void sound();
}

public class E_Solution implements Dog, Cat {
    /* 
    // 🔄 Option 1: Call specific interface's default method
    @Override
    public void sound(){
        Dog.super.sound(); // Explicitly calls Dog's default implementation
        // Cat.super.sound(); // ❌ Won't work - Cat has no default method
    }
    
    // 🔄 Option 2: Provide your own implementation (current approach)
    */

    @Override
    public void sound() {
        System.out.println("Cat meows"); // 💡 Custom implementation resolves the conflict
    }

    public static void main(String[] args) {
        E_Solution animal = new E_Solution();
        animal.sound(); // Outputs: "Cat meows"
    }
}