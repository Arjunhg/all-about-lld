package G_Projects.H_Car_Rental_System.MainRentalSystem;

import java.util.List;
import G_Projects.H_Car_Rental_System.UtilityClasses.RentalStore;

/*
 * RentalSystem: Manages the overall rental operations
 * 
 * Key Features:
 * • Centralized rental management system
 * • Implements Singleton pattern for single instance control
 * • Coordinates between multiple rental stores
 * • Handles vehicle inventory and reservations
 * • Provides unified interface for rental operations
 * 
 * Responsibilities:
 * • Managing rental store network
 * • Coordinating vehicle availability across stores
 * • Processing customer reservations
 * • Maintaining system-wide rental policies
 * • Ensuring data consistency across operations
 */

public class RentalSystem {
    private List<RentalStore> rentalStores;
    private VehicleFactory vehicleFactory;
    private ReservationManager reservationManager; // Manage user reservations
    private PaymentProcesser paymentProcessor;
    private List<SystemObserver> obersers;
}
