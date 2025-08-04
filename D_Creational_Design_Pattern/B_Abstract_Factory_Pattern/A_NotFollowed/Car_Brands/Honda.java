package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed.Car_Brands;

import D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed.Vehicle;

public class Honda implements Vehicle {
    public void start() {
        System.out.println("Honda is starting...");
    }

    public void stop() {
        System.out.println("Honda is stopping...");
    }
}