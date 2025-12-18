package UtilityClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CommonEnums.VehicleEnums.VehicleStatus;
import VehicleFactoryPattern.Vehicle;

// Manages opeartions related to rental store and contains vehicle inventory

public class RentalStore {
    private int id;
    private Location location;
    private String name;
    private Map<String, Vehicle> vehicleInventory; // Key: Vehicle Registration Number, Value: Vehicle Object

    public RentalStore(int id, String name, Location location){
        this.id = id;
        this.location = location;
        this.name = name;
        this.vehicleInventory = new HashMap<>();
    }
    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate){
        List<Vehicle> availableVehicles = new ArrayList<>();
        for(Vehicle vehicle : vehicleInventory.values()){
            if(vehicle.getStatus() == VehicleStatus.AVAILABLE){
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public void addVehicle(Vehicle vehicle){
        vehicleInventory.put(vehicle.getRegistrationNumber(), vehicle);
    }
    public void removeVehicle(String registrationNumber){
        vehicleInventory.remove(registrationNumber);
    }

    public boolean isVehicleAvailable(String registrationNumber, Date startDate, Date endDate){
        Vehicle vehicle = vehicleInventory.get(registrationNumber);
        return vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE;
    }

    public Vehicle getVehicle(String registrationNumber){
        return vehicleInventory.get(registrationNumber);
    }

    public Map<String, Vehicle> getVehicleInventory(){
        return vehicleInventory;
    }

    public int getId(){
        return id;
    }
    public Location getLocation(){
        return location;
    }
    public String getName(){
        return name;
    }

     
}
