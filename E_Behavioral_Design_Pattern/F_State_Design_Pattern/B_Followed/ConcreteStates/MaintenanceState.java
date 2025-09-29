package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.ConcreteStates;

import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightContext;
import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightState;

public class MaintenanceState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context){
        System.out.println("Exiting Maintenance Mode, switching to Red");
        context.setState(new RedState());
    }

    @Override
    public String getColor(){
        return "Maintenance";
    }
}
