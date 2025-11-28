package G_Projects.H_Car_Rental_System.VehicleFactoryPattern;

import G_Projects.H_Car_Rental_System.CommonEnums.VehicleEnums.VehicleType;
import G_Projects.H_Car_Rental_System.VehicleFactoryPattern.ConcreteVehicles.EconomyVehicle;
import G_Projects.H_Car_Rental_System.VehicleFactoryPattern.ConcreteVehicles.LuxuryVehicle;
import G_Projects.H_Car_Rental_System.VehicleFactoryPattern.ConcreteVehicles.SUVVehicle;

public class VehicleFactory {
    public Vehicle createVehicle(VehicleType vehicleType, String registrationNumber, String model, double baseRentalPrice){
        switch(vehicleType){
            case ECONOMY:
                return new EconomyVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            case LUXURY:
                return new LuxuryVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            case SUV:
                return new SUVVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
    }
}
