package UtilityClasses;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import VehicleFactoryPattern.Vehicle;

public class ReservationManager {
    private Map<Integer, Reservation> reservations;
    private int nextReservationId;

    public ReservationManager() {
        this.reservations = new HashMap<>();
        this.nextReservationId = 1;
    }

    public Reservation createReservation(User user, Vehicle vehicle, RentalStore pickupStore, RentalStore dropoffStore, Date startDate, Date endDate) {
        int reservationId = nextReservationId++;
        Reservation reservation = new Reservation(reservationId, user, vehicle, pickupStore, dropoffStore, startDate, endDate);
        reservations.put(reservationId, reservation);
        user.addReservation(reservation);
        return reservation;
    }

    public void confirmReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.confirmReservation();
        }
    }

    public void startRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.startRental();
        }
    }

    public void completeRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.completeRental();
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
        }
    }

    public Reservation getReservation(int reservationId) {
        return reservations.get(reservationId);
    }

}
