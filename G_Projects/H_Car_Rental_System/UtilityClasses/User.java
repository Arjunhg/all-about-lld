package UtilityClasses;

import java.util.ArrayList;
import java.util.List;

// Represents a user in the car rental system who can make reservations and interact with rental systems

public class User {
    private int userId;
    private String name;
    private String email;
    private List<Reservation> reservations;

    public User(int userId, String name, String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
    public void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
    }
    public List<Reservation> getReservations(){
        return reservations;
    }
    public int getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }   
}
