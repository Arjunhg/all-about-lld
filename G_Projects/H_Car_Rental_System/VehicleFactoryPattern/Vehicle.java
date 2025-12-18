package VehicleFactoryPattern;

import CommonEnums.VehicleEnums.VehicleStatus;
import CommonEnums.VehicleEnums.VehicleType;

public abstract class Vehicle {
    private String registrationNumber;
    private String model;
    private VehicleType type;
    private VehicleStatus status;
    private double baseRentalPrice;

    public Vehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.type = type;
        this.status = VehicleStatus.AVAILABLE; // Default status
        this.baseRentalPrice = baseRentalPrice;
    }

    public abstract double calculateRentalPrice(int days);

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public String getModel(){
        return model;
    }

    public VehicleType getType(){
        return type;
    }

    public VehicleStatus getStatus(){
        return status;
    }

    public void setStatus(VehicleStatus status){
        this.status = status;
    }

    public double getBaseRentalPrice(){
        return baseRentalPrice;
    }
}
