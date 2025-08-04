package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed.Car_Brands;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed.Vehicle;

public class BWM implements Vehicle {
    public void start(){
        System.out.println("BMW is starting...");
    }
    public void stop(){
        System.out.println("BMW is stopping...");
    }
}
