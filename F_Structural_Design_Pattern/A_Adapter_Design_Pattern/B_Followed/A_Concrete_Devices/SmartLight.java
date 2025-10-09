package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices;

public class SmartLight {
    
    public void connectToWiFi(){
        System.out.println("SmartLight connected via WiFi");
    }

    public void switchOn(){
        System.out.println("SmartLight is turned ON");
    }

    public void switchOff(){
        System.out.println("SmartLight is turned OFF");
    }

    public void disconnectWiFi(){
        System.out.println("SmartLight disconnected from WiFi");
    }
}
