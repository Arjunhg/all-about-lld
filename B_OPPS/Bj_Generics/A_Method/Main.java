package B_OPPS.Bj_Generics.A_Method;

/**
 * 🔧 GENERIC METHODS IN JAVA
 * 
 * 📝 DEFINITION:
 * A generic method is a method that can accept parameters and return values of different types 
 * while maintaining type safety. It uses type parameters (like <T>) that act as placeholders 
 * for actual types determined at runtime.
 * 
 * 🎯 KEY FEATURES:
 * • Type Parameters: Uses <T> syntax to define generic type placeholders
 * • Type Safety: Compiler ensures type correctness at compile-time
 * • Code Reusability: Single method works with multiple data types
 * • No Type Casting: Eliminates need for explicit type casting
 * 
 * ✨ ADVANTAGES:
 * • Eliminates long, individual type castings
 * • Provides compile-time type safety
 * • Enables writing more general and reusable code
 * • Reduces code duplication
 * 
 * 📖 SYNTAX:
 * <access_modifier> static <type_parameter> return_type method_name(type_parameter parameter)
 * 
 * Example: static <T> void genericDisplay(T element)
 */

public class Main {

    /**
     * 🔧 Generic Method Example
     * 
     * 📝 How it works:
     * • <T> declares this method as generic with type parameter T
     * • T element means the parameter can be of any type T
     * • getClass().getName() gets the actual runtime type of the element
     * • Compiler automatically infers the type based on the argument passed
     */
    static <T> void genericDisplay(T element){
        System.out.println(element.getClass().getName() + " = " + element);
    }

    public static void main(String[] args) {
        System.out.println("🚀 Generic Method Demonstration\n");
        
        // 🔢 Integer argument - T becomes Integer
        System.out.println("Integer Example:");
        genericDisplay(11);

        // 📝 String argument - T becomes String  
        System.out.println("\nString Example:");
        genericDisplay("Hello Generics");

        // 🔢 Double argument - T becomes Double
        System.out.println("\nDouble Example:");
        genericDisplay(3.14);
        
    }
    
}
