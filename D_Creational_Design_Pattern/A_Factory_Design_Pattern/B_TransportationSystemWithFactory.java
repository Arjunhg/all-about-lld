package D_Creational_Design_Pattern.A_Factory_Design_Pattern;

// Simplified Transportation System with Factory Pattern

interface Vehicle {
    void start();
    void stop();
    void displayInfo();
}

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

class VehicleFactory {
    public static Vehicle createVehicle(String type, String model) {
        switch (type.toLowerCase()) {
            case "car": return new Car(model);
            case "truck": return new Truck(model);
            case "bike": return new Bike(model);
            default: throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}

// Simplified DeliveryService using Factory
class DeliveryService {
    public void scheduleDelivery(String vehicleType, String destination) {
        System.out.println("\n=== Delivery Service ===");
        // All vehicle creation logic is centralized in the factory
        Vehicle deliveryVehicle = VehicleFactory.createVehicle(vehicleType, "Delivery Model");
        deliveryVehicle.displayInfo();
        deliveryVehicle.start();
        System.out.println("Delivering to: " + destination);
        deliveryVehicle.stop();
    }
}

// Simplified TaxiService using Factory
class TaxiService {
    public void bookRide(String vehicleType, String passengerName) {
        System.out.println("\n=== Taxi Service ===");
        // All vehicle creation logic is centralized in the factory
        Vehicle taxiVehicle = VehicleFactory.createVehicle(vehicleType, "Taxi Model");
        taxiVehicle.displayInfo();
        taxiVehicle.start();
        System.out.println("Picking up passenger: " + passengerName);
        taxiVehicle.stop();
    }
}

public class B_TransportationSystemWithFactory {
    public static void main(String[] args) {
        // Demonstrate centralized object creation with Factory
        DeliveryService delivery = new DeliveryService();
        delivery.scheduleDelivery("car", "Downtown");
        delivery.scheduleDelivery("truck", "Warehouse District");
        delivery.scheduleDelivery("bike", "Green Building Complex");

        TaxiService taxi = new TaxiService();
        taxi.bookRide("car", "John Doe");
        taxi.bookRide("bike", "Jane Smith");

        // Advantages of Factory Pattern: For details refer to TSWF.md
        // 1. Centralized vehicle creation logic (easy to maintain and extend)
        // 2. Business classes (DeliveryService, TaxiService) are clean and focused
        // 3. Adding new vehicle types only requires changes in VehicleFactory
        // 4. Reduces code duplication (DRY principle)
        // 5. Makes testing and logging easier (all creation in one place)
    }
}