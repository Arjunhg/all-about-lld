package UtilityClasses;

import java.util.Date;

import CommonEnums.ReservationStatus;
import CommonEnums.VehicleEnums.VehicleStatus;
import VehicleFactoryPattern.Vehicle;

/*
 * Reservation Class
 * -----------------
 * This class handles:
 *   • Managing vehicle bookings
 *   • Storing details about:
 *       - The user making the reservation
 *       - The vehicle being rented
 *       - Store locations (pickup & drop-off)
 *       - Rental period (start & end dates)
 */

public class Reservation {
    private int reservationId;
    private User user;
    private Vehicle vehicle;
    private RentalStore pickupRentalStore;
    private RentalStore dropoffRentalStore;
    private Date startDate;
    private Date endDate;
    private ReservationStatus status;
    private double totalCost;

    public Reservation(int reservationId, User user, Vehicle vehicle, RentalStore pickupRentalStore, RentalStore dropoffRentalStore, Date startDate, Date endDate) {
        this.reservationId = reservationId;
        this.user = user;
        this.vehicle = vehicle;
        this.pickupRentalStore = pickupRentalStore;
        this.dropoffRentalStore = dropoffRentalStore;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;

        long diffInMillies = endDate.getTime() - startDate.getTime();
        int days = (int)(diffInMillies / (1000 * 60 * 60 * 24)) + 1; // +1 to include both start and end date
        this.totalCost = vehicle.calculateRentalPrice(days);
    }

    public void confirmReservation() {
        if(status == ReservationStatus.PENDING){
            status = ReservationStatus.CONFIRMED;
            vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }

    public void startRental() {
        if (status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.IN_PROGRESS;
            vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental() {
        if (status == ReservationStatus.IN_PROGRESS) {
            status = ReservationStatus.COMPLETED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void cancelReservation() {
        if (status == ReservationStatus.PENDING
                || status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.CANCELED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public int getReservationId() {
        return reservationId;
    }
    public User getUser() {
        return user;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public RentalStore getPickupRentalStore() {
        return pickupRentalStore;
    }
    public RentalStore getDropoffRentalStore() {
        return dropoffRentalStore;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public ReservationStatus getStatus() {
        return status;
    }
    public double getTotalCost() {
        return totalCost;
    }
}
