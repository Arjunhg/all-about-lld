package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.C_Client_Code;

import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.SmartDevice;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices.AirConditioner;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices.CoffeeMachine;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices.SmartLight;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.B_Concrete_Adapters.AirConditionerAdapter;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.B_Concrete_Adapters.CoffeeMachineAdapter;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.B_Concrete_Adapters.SmartLightAdapter;

/**
 * SmartHomeController - Client Code Implementation
 * 
 * Key Benefits of Adapter Pattern Implementation:
 * 
 * • Simplified Controller Logic:
 *   - No longer handles device-specific communication protocols
 *   - Eliminates complex conditional logic for different device types
 *   - Focuses solely on high-level smart home orchestration
 * 
 * • Unified Device Interface:
 *   - Interacts with all devices through the common SmartDevice interface
 *   - Consistent method calls regardless of underlying device technology
 *   - Standardized approach to device management
 * 
 * • Adapter-Managed Communication:
 *   - Each adapter handles its specific device's communication requirements
 *   - Protocol translation happens transparently in adapter classes
 *   - Controller remains unaware of individual device complexities
 * 
 * • Enhanced Maintainability:
 *   - Adding new device types requires only new adapters
 *   - Controller code remains stable and unchanged
 *   - Clear separation of concerns between client and device implementations
 */

public class SmartHomeController {
    public static void main(String[] args) {
        
        SmartDevice airConditioner = new AirConditionerAdapter(new AirConditioner());
        SmartDevice coffDevice = new CoffeeMachineAdapter(new CoffeeMachine());
        SmartDevice smartLight = new SmartLightAdapter(new SmartLight());

        // Control devices using the common interface
        airConditioner.turnOn();
        coffDevice.turnOn();
        smartLight.turnOn();
        System.out.println("----- All devices are running -----");
        airConditioner.turnOff();
        coffDevice.turnOff();
        smartLight.turnOff();
        System.out.println("----- All devices are turned off -----");
    }
}
