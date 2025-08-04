package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.B_Car_Brands;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.B_Followed.A_Interfaces.Vehicle;

public class BMW implements Vehicle {
    public void start(){
        System.out.println("BMW is starting...");
    }
    public void stop(){
        System.out.println("BMW is stopping...");
    }
}
