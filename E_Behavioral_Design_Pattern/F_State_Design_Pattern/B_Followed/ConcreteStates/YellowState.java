package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.ConcreteStates;

import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightContext;
import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.TrafficLightState;

public class YellowState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context){
        System.out.println("Changing from Yellow to Blinking");
        context.setState(new BlinkingState());
    }

    @Override
    public String getColor(){
        return "Yellow";
    }
}
