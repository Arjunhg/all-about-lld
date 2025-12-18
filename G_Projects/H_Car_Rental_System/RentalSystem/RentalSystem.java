
/*
 * Welcome to the RentalSystem class! Here’s what it does:
 * 
 * - Acts as the main class for managing the entire car rental service.
 * - Implements the Singleton pattern, so only one instance exists throughout the application.
 * - Handles all core components:
 *     • Users: Manages user information and registration.
 *     • Stores: Keeps track of all rental store locations.
 *     • Reservations: Manages bookings and reservations.
 *     • Payments: Processes all payment transactions.
 * 
 * This class brings together all the moving parts of the car rental system!
 */

package  RentalSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  PaymentStrategy.PaymentProcessor;
import  PaymentStrategy.PaymentStrategy;
import  UtilityClasses.RentalStore;
import  UtilityClasses.Reservation;
import  UtilityClasses.ReservationManager;
import  UtilityClasses.User;
import  VehicleFactoryPattern.Vehicle;
import  VehicleFactoryPattern.VehicleFactory;

public class RentalSystem {
    private static RentalSystem instance;
    private List<RentalStore> stores;
    private VehicleFactory vehicleFactory;
    private ReservationManager reservationManager;
    private PaymentProcessor paymentProcessor;
    private Map<Integer, User> users;
    private int nextUserId;

    private RentalSystem(){
        this.stores = new ArrayList<>();
        this.vehicleFactory = new VehicleFactory();
        this.reservationManager = new ReservationManager();
        this.paymentProcessor = new PaymentProcessor();
        this.nextUserId = 1;
        this.users = new HashMap<>();
    }

    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    public void addStore(RentalStore store) {
        stores.add(store);
    }

    public RentalStore getStore(int storeId) {
        for (RentalStore store : stores) {
            if (store.getId() == storeId) {
                return store;
            }
        }
        return null;
    }

    public List<RentalStore> getStores() {
        return stores;
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

     public Reservation createReservation(int userId, String vehicleRegistration,
        int pickupStoreId, int returnStoreId, Date startDate, Date endDate) {
        User user = users.get(userId);
        RentalStore pickupStore = getStore(pickupStoreId);
        RentalStore returnStore = getStore(returnStoreId);
        Vehicle vehicle = (pickupStore != null) ? pickupStore.getVehicle(vehicleRegistration): null;

        if (user != null && pickupStore != null && returnStore != null && vehicle != null) {
            return reservationManager.createReservation(
                user, vehicle, pickupStore, returnStore, startDate, endDate);
        }
        return null;
    }

    public boolean processPayment(
        int reservationId, PaymentStrategy paymentStrategy) {
        Reservation reservation =
            reservationManager.getReservation(reservationId);
        if (reservation != null) {
            boolean result = paymentProcessor.processPayment(paymentStrategy, reservation.getTotalCost());
            if (result) {
                reservationManager.confirmReservation(reservationId);
                return true;
            }
        }
        return false;
    }

    public void startRental(int reservationId) {
        reservationManager.startRental(reservationId);
    }

    public void completeRental(int reservationId) {
        reservationManager.completeRental(reservationId);
    }

    public void cancelReservation(int reservationId) {
        reservationManager.cancelReservation(reservationId);
    }

    public void registerUser(User user){
        int userID = user.getUserId();
        if(users.containsKey(userID)){
            System.out.println("User with id : " + userID + "Already exists in the system");
            return;
        }
        users.put(userID , user);
    }
}
