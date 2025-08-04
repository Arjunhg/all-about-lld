package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed.Car_Brands.*;

public class CarFactory {
    public Vehicle createVehicle(String brand){
        if(brand.equals("Honda")){
            return new Honda();
        } else if(brand.equals("BWM")){
            return new BWM();
        } else if(brand.equals("Toyota")){
            return new Toyota();
        } else {
            throw new IllegalArgumentException("Unknown brand: " + brand);
        }
    }
}
