package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.B_Concrete_Adapters;

import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.SmartDevice;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices.SmartLight;

public class SmartLightAdapter implements SmartDevice {
    
    private SmartLight smartLight;

    public SmartLightAdapter(SmartLight smartLight){
        this.smartLight = smartLight;
    }

    @Override
    public void turnOn(){
        smartLight.connectToWiFi();
        smartLight.switchOn();
    }

    @Override
    public void turnOff(){
        smartLight.switchOff();
        smartLight.disconnectWiFi();
    }
}
