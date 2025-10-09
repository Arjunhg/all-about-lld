package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.B_Concrete_Adapters;

import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.SmartDevice;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices.AirConditioner;

// Step 3 : Create Adapter classes for each device implementing the Target interface

public class AirConditionerAdapter implements SmartDevice {

    private AirConditioner airConditioner;

    public AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void turnOn(){
        airConditioner.connectToBluetooth();
        airConditioner.startCooling();
    }

    @Override
    public void turnOff(){
        airConditioner.stopCooling();
        airConditioner.disconnectBluetooth();
    }
    
}
