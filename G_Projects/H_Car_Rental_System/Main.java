
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import CommonEnums.VehicleEnums.VehicleType;
import PaymentStrategy.PaymentStrategy;
import PaymentStrategy.ConcretePaymentStrategies.CashPayment;
import PaymentStrategy.ConcretePaymentStrategies.CreditCardPayment;
import PaymentStrategy.ConcretePaymentStrategies.PaypalPayment;
import RentalSystem.RentalSystem;
import UtilityClasses.Location;
import UtilityClasses.RentalStore;
import UtilityClasses.Reservation;
import UtilityClasses.User;
import VehicleFactoryPattern.Vehicle;
import VehicleFactoryPattern.VehicleFactory;


public class Main {
    public static void main(String[] args) {
        // Get the Rental System instance (Singleton)
        RentalSystem rentalSystem = RentalSystem.getInstance();

        // Create rental stores
        RentalStore store1 = new RentalStore(
                1, "Downtown Rentals", new Location("123 Main St", "New York", "NY", "10001"));
        RentalStore store2 = new RentalStore(
                2, "Airport Rentals", new Location("456 Airport Rd", "Los Angeles", "CA", "90045"));
        rentalSystem.addStore(store1);
        rentalSystem.addStore(store2);

        // Create vehicles using Factory Pattern
        Vehicle economyCar = VehicleFactory.createVehicle(
                VehicleType.ECONOMY, "EC001", "Toyota",  50.0);
        Vehicle luxuryCar = VehicleFactory.createVehicle(
                VehicleType.LUXURY, "LX001", "Mercedes",  200.0);
        Vehicle suvCar =
                VehicleFactory.createVehicle(VehicleType.SUV, "SV001", "Honda",  75.0);

        // Add vehicles to stores
        store1.addVehicle(economyCar);
        store1.addVehicle(luxuryCar);
        store2.addVehicle(suvCar);

        // List Vehicles for store 1 :
        Map<String, Vehicle> vehicles = store1.getVehicleInventory();
        for(Map.Entry<String , Vehicle> entry : vehicles.entrySet()){
            System.out.println("Vehicle Id : " + entry.getKey() + "Vehicle Type : "
            + entry.getValue().getType() + "Vehicle Number: " + entry.getValue().getRegistrationNumber());
        }

        // Register user
        User user1 = new User(123, "ABC" , "abc@gmail.com");
        User user2 = new User(345 , "BCD" , "bcd@yahoo.com");

        rentalSystem.registerUser(user1);
        rentalSystem.registerUser(user2);

        // Create reservations
        Reservation reservation1 = rentalSystem.createReservation(user1.getUserId(), economyCar.getRegistrationNumber(),
                        store1.getId(), store1.getId(), new Date(2025 - 1900, 4, 1),
                new Date(2025 - 1900, 5, 15));

        // Process payment using different strategies (Strategy Pattern)
        Scanner scanner = new Scanner(System.in);
        System.out.println("nProcessing payment for reservation #" + reservation1.getReservationId());
        System.out.println("Total amount: $" + reservation1.getTotalCost());
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.println("3. PayPal");

        int choice = 1; // Default to credit card for this example
        // In a real application, you would get user input:
        // int choice = scanner.nextInt();
        PaymentStrategy paymentStrategy;
        switch (choice) {
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new CashPayment();
                break;
            case 3:
                paymentStrategy = new PaypalPayment();
                break;
            default:
                System.out.println("Invalid choice! Defaulting to credit card payment.");
                paymentStrategy = new CreditCardPayment();
                break;
        }
        boolean paymentSuccess = rentalSystem.processPayment(reservation1.getReservationId(), paymentStrategy);
        if (paymentSuccess) {
            System.out.println("Payment successful!");

            // Start the rental
            rentalSystem.startRental(reservation1.getReservationId());

            // Simulate rental period
            System.out.println("nSimulating rental period...");

            // Complete the rental
            rentalSystem.completeRental(reservation1.getReservationId());
        } else {
            System.out.println("Payment failed!");
        }
    }
    }

