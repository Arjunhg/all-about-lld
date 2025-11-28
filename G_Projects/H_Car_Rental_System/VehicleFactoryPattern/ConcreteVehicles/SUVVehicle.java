package G_Projects.H_Car_Rental_System.VehicleFactoryPattern.ConcreteVehicles;

import G_Projects.H_Car_Rental_System.CommonEnums.VehicleEnums.VehicleType;
import G_Projects.H_Car_Rental_System.VehicleFactoryPattern.Vehicle;

public class SUVVehicle extends Vehicle{
    private static final double RATE_MULTIPLIER = 1.5;

    public SUVVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice){
        super(registrationNumber, model, type, baseRentalPrice); 
    }

    @Override
    public double calculateRentalPrice(int days){
        return getBaseRentalPrice() * RATE_MULTIPLIER * days;
    }
}
