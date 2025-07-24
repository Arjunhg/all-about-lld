package D_Creational_Design_Pattern.A_Factory_Design_Pattern;

//Base Vehicle interface - this stays the same in both approaches
interface Vehicle {
    void start();
    void stop();
    void displayInfo();
}

// Concrete classes for different types of vehicles
class Car implements Vehicle {
    private String model;
    public Car(String model) { this.model = model; }
    public void start() { System.out.println(model + " car starting..."); }
    public void stop() { System.out.println(model + " car stopping..."); }
    public void displayInfo() { System.out.println("Car: " + model); }
}

class Truck implements Vehicle {
    private String model;
    public Truck(String model) { this.model = model; }
    public void start() { System.out.println(model + " truck starting..."); }
    public void stop() { System.out.println(model + " truck stopping..."); }
    public void displayInfo() { System.out.println("Truck: " + model); }
}

class Bike implements Vehicle {
    private String model;
    public Bike(String model) { this.model = model; }
    public void start() { System.out.println(model + " bike starting..."); }
    public void stop() { System.out.println(model + " bike stopping..."); }
    public void displayInfo() { System.out.println("Bike: " + model); }
}
// ========================================
// BUSINESS LOGIC CLASSES - Notice the scattered vehicle creation logic
// ========================================

class DeliveryService {
    public void scheduleDelivery(String deliveryType, String destination){
        System.out.println("\n=== Delivery Service ===");
        Vehicle deliveryVehicle;

        // PROBLEM 1: Hard-coded object creation logic scattered everywhere
        if (deliveryType.equals("standard")) {
            deliveryVehicle = new Car("Honda Civic");
        } else if (deliveryType.equals("heavy")) {
            deliveryVehicle = new Truck("Volvo FH16");
        } else {
            System.out.println("Invalid delivery type.");
            return;
        }

        deliveryVehicle.displayInfo();
        deliveryVehicle.start();
        System.out.println("Delivering to: " + destination);
        deliveryVehicle.stop();
    }
}

class TaxiService {
    public void bookRide(String rideType, String passengerName) {
        System.out.println("\n=== Taxi Service ===");
        Vehicle taxiVehicle;
        
        // PROBLEM 1: Same vehicle creation logic duplicated here
        if (rideType.equals("economy")) {
            taxiVehicle = new Car("Toyota Corolla");
        } else if (rideType.equals("premium")) {
            taxiVehicle = new Car("BMW X5");
        } else {
            throw new IllegalArgumentException("Unknown ride type: " + rideType);
        }
        
        taxiVehicle.displayInfo();
        taxiVehicle.start();
        System.out.println("Picking up passenger: " + passengerName);
        taxiVehicle.stop();
    }
}


public class A_TransportationSystemWithoutFactory {
    public static void main(String[] args) {
        
        // Using DeliveryService
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.scheduleDelivery("standard", "123 Main St");
        deliveryService.scheduleDelivery("heavy", "456 Industrial Ave");

        // Using TaxiService
        TaxiService taxiService = new TaxiService();
        taxiService.bookRide("economy", "Alice");
        taxiService.bookRide("premium", "Bob");

        // === PROBLEMS WITH THIS APPROACH: For details refer to TSWOF.md ===
        // 1. Vehicle creation logic is duplicated in 2 different classes
        // 2. If we need to add logging to vehicle creation, we must modify 2 classes
        // 3. If we add a new vehicle type (e.g., Electric Car), we must update 2 classes
        // 4. If constructor parameters change, we must update 2 classes
        // 5. No centralized control over vehicle creation and initialization
        // 6. Testing becomes harder - we can't easily mock vehicle creation
        // 7. Code violates DRY, OC, SR principle
    }
}
