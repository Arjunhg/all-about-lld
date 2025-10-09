package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.B_Concrete_Adapters;

import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.SmartDevice;
import F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices.CoffeeMachine;

public class CoffeeMachineAdapter implements SmartDevice {
    
    private CoffeeMachine coffeeMachine;

    public CoffeeMachineAdapter(CoffeeMachine coffeeMachine){
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void turnOn(){
        coffeeMachine.initialize();
        coffeeMachine.brewCoffee();
    }

    @Override
    public void turnOff(){
        coffeeMachine.stopBrewing();
        coffeeMachine.shutdown();
    }
}
