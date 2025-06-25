package C_Design_Principles.A_SOLID_Principle.C_LiskovSubstitution_Principle;

// To properly implement the Liskov Substitution Principle, consider the following points:
// 1. Start with a base abstraction (Vehicle) that defines common behavior for all vehicles.
// 2. Create specialized abstractions for different categories, such as EngineVehicle (for motorized vehicles) and NonEngineVehicle (for non-motorized vehicles).
// 3. This segregation ensures:
//    - Each subclass only inherits relevant behaviors.
//    - Motorized and non-motorized vehicles are clearly distinguished.
//    - Substitution of subclasses for their parent types remains safe and logical.
// 4. This approach improves code clarity, maintainability, and adherence to SOLID principles.

// Using abstract classes
abstract class Vehicle {
    // common properties and methods for all vehicles
    public void move(){
        System.out.println("Vehicle is moving");
    }
}

abstract class EngineVehicle extends Vehicle {
    // common properties and methods for vehicles with engines
    public void startEngine(){
        System.out.println("Engine started");
    }
}

abstract class NonEngineVehicle extends Vehicle {
    // common properties and methods for vehicles without engines
    public void pedal(){
        System.out.println("Pedaling the vehicle");
    }
}

class Car extends EngineVehicle{
    @Override
    public void startEngine() {
        System.out.println("Car engine started");
    }
}

class Bicycle extends NonEngineVehicle {
    @Override
    public void pedal() {
        System.out.println("Bicycle is being pedaled");
    }
}

/* Using interafces
interface Vehicle {
    void move();
}

interface EngineVehicle extends Vehicle {
    void startEngine();
}
interface NonEngineVehicle extends Vehicle {
    void pedal();
}

class Car implements EngineVehicle {
    @Override
    public void startEngine() {
        System.out.println("Car engine started");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

class Bicycle implements NonEngineVehicle {
    @Override
    public void pedal() {
        System.out.println("Bicycle is being pedaled");
    }

    @Override
    public void move() {
        System.out.println("Bicycle is moving");
    }
}

/*What if we made move() method in interface as deafult or static?
 | Type       | Can be overridden? | Inherited by implementing class? | Typical Use Case                     |
 | ---------- | ------------------ | -------------------------------- | ------------------------------------ |
| `abstract` | ✅ Yes              | ✅ Yes                            | Force each class to implement        |
| `default`  | ✅ Yes              | ✅ Yes                            | Provide shared but overridable logic |
| `static`   | ❌ No               | ❌ No                             | Utility methods only                 |
*/


public class B_Followed {
    public static void main(String[] args) {

        EngineVehicle myCar = new Car();
        myCar.startEngine(); // Output: Car engine started
        myCar.move(); // Output: Car is moving

        NonEngineVehicle myBicycle = new Bicycle();
        myBicycle.pedal(); // Output: Bicycle is being pedaled
        myBicycle.move(); // Output: Bicycle is moving

        /* Using interface
            EngineVehicle myCar = new Car();
            myCar.startEngine(); // Output: Car engine started
            myCar.move(); // Output: Vehicle is moving

            NonEngineVehicle myBicycle = new Bicycle();
            myBicycle.pedal(); // Output: Bicycle is being pedaled
        */
    }
}
