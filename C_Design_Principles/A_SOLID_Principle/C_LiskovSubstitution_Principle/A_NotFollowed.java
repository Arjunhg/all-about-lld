package C_Design_Principles.A_SOLID_Principle.C_LiskovSubstitution_Principle;

// Example violating Liskov Substitution Principle (LSP):
// 1. Vehicle class defines a startEngine() method.
// 2. Car subclass works fine, as cars have engines.
// 3. Bicycle subclass violates LSP because bicycles do not have engines.
//    - Bicycle overrides startEngine() to throw an exception.
//    - This breaks substitutability: not all Vehicle subclasses can be used interchangeably.

class Vehicle {
    void startEngine() {
        System.out.println("Engine started");
    }
}

class Car extends Vehicle {
    @Override
    void startEngine() {
        System.out.println("Car engine started");
    }
}

class Bicycle extends Vehicle {
    @Override
    void startEngine() {
        // Problem: Bicycles don't have engines!
        throw new UnsupportedOperationException("Bicycles do not have engines");
    }
}

public class A_NotFollowed {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        Vehicle myBicycle = new Bicycle();

        myCar.startEngine(); // Output: Car engine started. Runtime polymorphism.

        try {
            myBicycle.startEngine(); // Throws exception: UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
