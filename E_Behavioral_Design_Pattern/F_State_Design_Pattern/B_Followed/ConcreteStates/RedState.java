package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.ConcreteStates;

import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightContext;
import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightState;

public class RedState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context){
        System.out.println("Changing from Red to Green");
        // Each state's next defines the transition to the next state
        context.setState(new GreenState());
    }
    @Override
    public String getColor(){
        return "Red";
    }
}
