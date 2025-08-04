package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.A_Interfaces.Vehicle;
import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.A_Interfaces.VehicleFactory;
import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.C_Factories.HondaFactory;
import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.C_Factories.ToyotaFactory;


public class Main {
    public static void main(String[] args) {
        VehicleFactory hondaFactory = new HondaFactory();
        Vehicle honda = hondaFactory.createVehicle();
        honda.start();
        honda.stop();

        VehicleFactory toyotaFactory = new ToyotaFactory();
        Vehicle toyota = toyotaFactory.createVehicle();
        toyota.start();
        toyota.stop();
    }
}