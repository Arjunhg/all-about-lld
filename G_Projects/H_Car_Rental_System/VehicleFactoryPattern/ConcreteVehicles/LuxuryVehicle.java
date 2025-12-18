package VehicleFactoryPattern.ConcreteVehicles;

import CommonEnums.VehicleEnums.VehicleType;
import VehicleFactoryPattern.Vehicle;

public class LuxuryVehicle extends Vehicle{
    private static final double RATE_MULTIPLIER = 1.0;
    private static final double PREMIUM_FEE = 50.0;

    public LuxuryVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice){
        super(registrationNumber, model, type, baseRentalPrice); 
    }

    @Override
    public double calculateRentalPrice(int days){
        return (getBaseRentalPrice() * RATE_MULTIPLIER * days) + PREMIUM_FEE;
    }
}
