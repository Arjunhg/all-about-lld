package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices;

// Step 2 : Create Concrete classes for Each Device

public class AirConditioner {
    
    public void connectToBluetooth(){
        System.out.println("AirConditioner connected via Bluetooth");
    }

    public void startCooling(){
        System.out.println("AirConditioner is cooling the room");
    }   

    public void stopCooling(){
        System.out.println("AirConditioner has stopped cooling");
    }

    public void disconnectBluetooth(){
        System.out.println("AirConditioner disconnected from Bluetooth");
    }
}
