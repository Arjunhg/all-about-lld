package F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.C_Client_Code;

import F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.SmartComponent;
import F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.A_Concreate_Devices.AirConditioner;
import F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.A_Concreate_Devices.SmartLight;
import F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.B_Composite_Class.CompositeSmartComponent;

public class SmartHomeController {
    public static void main(String[] args) {
        
        // Individual devices
        SmartComponent airConditioner = new AirConditioner();
        SmartComponent smartLight = new SmartLight();

        // Create room and add devices
        CompositeSmartComponent room1 = new CompositeSmartComponent();
        room1.addComponent(airConditioner);
        room1.addComponent(smartLight);

        // Add more rooms
        CompositeSmartComponent room2 = new CompositeSmartComponent();
        room2.addComponent(new SmartLight());
        room2.addComponent(new AirConditioner());

        // Create floor and add rooms
        CompositeSmartComponent floor1 = new CompositeSmartComponent();
        floor1.addComponent(room1);
        floor1.addComponent(room2);

        // Create house and add floors
        CompositeSmartComponent home = new CompositeSmartComponent();
        home.addComponent(floor1);

        // Control entire home
        System.out.println("Turning on the entire home:");
        home.turnOn();
        System.out.println("\nTurning off the entire home:");
        home.turnOff();

        // Control single floor
        System.out.println("\nTurning on floor 1:");
        floor1.turnOn();
        System.out.println("\nTurning off floor 1:");
        floor1.turnOff();

        // Control single room
        System.out.println("\nTurning on room 1:");
        room1.turnOn();
        System.out.println("\nTurning off room 1:");
        room1.turnOff();
    }
}
