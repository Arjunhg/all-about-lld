package B_OPPS.Bf_Polymorphism;
import java.util.ArrayList;
import java.util.List;

/*
 * 🐛 Polymorphism Disadvantage: Complex Debugging
 * 
 * This example demonstrates how polymorphism can make debugging challenging
 * when you can't easily identify which specific object type is being executed.
 */

class Vehicle {
    // 🔄 Instance method - resolved at RUNTIME (Dynamic Dispatch)
    void start(){
        System.out.println("Dynamic: Vehicle is starting");
    }

    // ⚡ Static method - resolved at COMPILE-TIME (Static Dispatch)
    static void describe(){
        System.out.println("Static: This is a vehicle");
    }
}

class Car extends Vehicle {
    // ✅ @Override works for instance methods
    // 🎯 Resolved based on OBJECT type: new Car() or new Vehicle()
    @Override
    void start(){
        System.out.println("Dynamic: Car is starting");
    }

    // ❌ @Override won't work for static methods - they can't be overridden!
    // 🎯 Resolved based on REFERENCE type: Vehicle v or Car c
    // 💡 Why? Static methods are bound to reference type at compile-time
    static void describe(){
        System.out.println("Static: This is a car");
    }
}

class Bike extends Vehicle {
    @Override
    void start(){
        System.out.println("Bike is starting");
    }
}

class Truck extends Vehicle {
    @Override
    void start(){
        System.out.println("Truck is starting");
    }
}

public class C_Disadvantage {
    public static void main(String[] args) {
        // 📦 Creating a list of vehicles for polymorphic behavior
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Car());
        vehicleList.add(new Bike());
        vehicleList.add(new Truck());

        // 🐛 DEBUGGING CHALLENGE: Which specific vehicle is starting?
        // 💭 Easy here, but imagine if this list came from a database or external API!
        System.out.println("=== Polymorphic Method Calls ===");
        for(Vehicle vehicle : vehicleList){
            vehicle.start(); // 🤔 Hard to debug which exact type is executing
        }

        // 📊 Performance Comparison: Static vs Dynamic Dispatch
        System.out.println("\n=== Static vs Dynamic Dispatch Benchmark ===");
        int iterations = 100;

        // ⚡ Static Dispatch - FASTER (compile-time resolution)
        long staticStart = System.nanoTime();
        // Vehicle staticVehicle = new Car();
        for (int i = 0; i < iterations; i++) {
            Vehicle.describe(); // Always calls Vehicle's version (bound to reference type)
            // staticVehicle.describe(); works too and calls vehicle version but with a warning
        }
        long staticEnd = System.nanoTime();

        // 🔄 Dynamic Dispatch - SLOWER (runtime resolution)
        Vehicle dynamicVehicle = new Car();
        long dynamicStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            dynamicVehicle.start(); // Method looked up at runtime
        }
        long dynamicEnd = System.nanoTime();

        // 📈 Results
        System.out.println("Time taken for static dispatch:   " + (staticEnd - staticStart) + " ns");
        System.out.println("Time taken for dynamic dispatch: " + (dynamicEnd - dynamicStart) + " ns");

        // 🎯 Key Tradeoff: Static = Speed ⚡ | Instance = Flexibility 🔄 (Polymorphism)
    }
}