package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.B_Car_Brands;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.A_Interfaces.Vehicle;

public class Toyota implements Vehicle {
    public void start(){
        System.out.println("Toyota is starting...");
    }
    public void stop(){
        System.out.println("Toyota is stopping...");
    }
}