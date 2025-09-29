package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.ConcreteStates;

import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightContext;
import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightState;

public class BlinkingState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context){
        System.out.println("Exiting Blinking Mode, switching to Maintenance Mode");
        context.setState(new MaintenanceState());
    }

    @Override
    public String getColor(){
        return "Blinking";
    }
}
