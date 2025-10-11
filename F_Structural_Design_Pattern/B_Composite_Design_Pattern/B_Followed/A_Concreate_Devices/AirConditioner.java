package F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.A_Concreate_Devices;

import F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.SmartComponent;

public class AirConditioner implements SmartComponent {
    @Override
    public void turnOn() {
        System.out.println("Turning on AC");
    }
    @Override
    public void turnOff() {
        System.out.println("Turning off AC");
    }
}
