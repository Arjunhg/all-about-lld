/**
 * 🔧 GENERIC CLASSES IN JAVA
 * 
 * 📝 DEFINITION:
 * A generic class is implemented exactly like a non-generic class, with the key difference
 * being that it contains a type parameter section. Generic classes can accept one or more
 * type parameters, making them parameterized classes or parameterized types.
 * 
 * 🎯 KEY FEATURES:
 * • Type Parameters: Uses <T> syntax to define generic type placeholders
 * • Multiple Parameters: Can have multiple type parameters like <T, U, V>
 * • Type Safety: Compiler ensures type correctness at compile-time
 * • Code Reusability: Single class works with multiple data types
 */

package B_OPPS.J_Generics.B_Class;

// Single type parameter class
class Test<T> {
    // 📦 Instance variable of generic type T
    // This can hold any object type specified during instantiation
    T obj;
  
    Test(T obj){
        this.obj = obj;
    }

    /**
     * Without generics: Object getObject() - would need casting
     * With generics: T getObject() - type-safe return
     */
    public T getObject(){
        return obj;
    }

    public void displayInfo() {
        System.out.println("Type: " + obj.getClass().getSimpleName() + 
                          ", Value: " + obj);
    }
}

// Multiple type parameters class
class Pair<T, U> {
    T obj1;
    U obj2;

    Pair(T obj1, U obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public void print(){
        System.out.println("Object 1: " + obj1.getClass().getName() + " = " + obj1);
        System.out.println("Object 2: " + obj2.getClass().getName() + " = " + obj2);
    }
    
    public T getFirst() { return obj1; }
    public U getSecond() { return obj2; }
    
    public void setFirst(T obj1) { this.obj1 = obj1; }
    public void setSecond(U obj2) { this.obj2 = obj2; }
}

public class Main {
    public static void main(String[] args) {
        
        // 🎯 DEMONSTRATION 1: Single Type Parameter Generic Class
        System.out.println("=== SINGLE TYPE PARAMETER EXAMPLES ===");
        
        // Creating Test object with Integer type
        Test<Integer> intObj = new Test<>(15);
        System.out.println("Integer Value: " + intObj.getObject());
        intObj.displayInfo();
        
        // Creating Test object with String type
        Test<String> strObj = new Test<String>("Generics in Java");
        System.out.println("String Value: " + strObj.getObject());
        strObj.displayInfo();
        
        // 💡 TYPE SAFETY DEMONSTRATION:
        // Integer value = intObj.getObject(); // No casting needed!
        // String text = strObj.getObject();   // No casting needed!
        
        System.out.println("\n=== MULTIPLE TYPE PARAMETER EXAMPLES ===");
        
        // 🎯 DEMONSTRATION 2: Multiple Type Parameters Generic Class
        Pair<Integer, String> pair1 = new Pair<>(42, "Hello World");
        pair1.print();
        
        // Different type combinations
        Pair<String, Boolean> pair2 = new Pair<>("Success", true);
        pair2.print();
        
        Pair<Double, Character> pair3 = new Pair<>(3.14, 'π');
        pair3.print();
        
        System.out.println("\n=== TYPE SAFETY & FLEXIBILITY DEMO ===");
        
        // 🔒 TYPE SAFETY: Compiler prevents type mismatches
        // pair1.setFirst("String"); // ❌ Compile error - expects Integer
        // pair1.setSecond(123);     // ❌ Compile error - expects String
        
        // ✅ Type-safe operations
        Integer firstValue = pair1.getFirst();  // No casting needed
        String secondValue = pair1.getSecond(); // No casting needed
        
        System.out.println("Type-safe retrieval:");
        System.out.println("First (Integer): " + firstValue);
        System.out.println("Second (String): " + secondValue);
        
        // 🔄 UPDATING VALUES (Type-safe)
        pair1.setFirst(100);
        pair1.setSecond("Updated Text");
        System.out.println("\nAfter updates:");
        pair1.print();
        
        System.out.println("\n=== PRACTICAL EXAMPLE: COORDINATES ===");
        
        // Real-world example: Coordinate system
        Pair<Double, Double> coordinates = new Pair<>(10.5, 20.3);
        System.out.println("Point coordinates:");
        System.out.println("X: " + coordinates.getFirst());
        System.out.println("Y: " + coordinates.getSecond());
    }
}
