package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.C_Factories;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.A_Interfaces.Vehicle;
import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.B_Car_Brands.Honda;
import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.A_Interfaces.VehicleFactory;

public class HondaFactory implements VehicleFactory{
    public Vehicle createVehicle(){
        return new Honda();
    }
}

