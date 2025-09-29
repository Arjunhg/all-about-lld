package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed;

import E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed.ConcreteStates.RedState;

// Context Class for Traffic Light State Management
// • Maintains a reference to the current state object
// • Delegates state-specific behavior to the current state
// • Provides interface for state transitions and operations
// • Acts as the main entry point for client interactions

public class TrafficLightContext {
    private TrafficLightState currentState; //Reference to current state

    public TrafficLightContext(){
        currentState = new RedState();
    }

    public void setState(TrafficLightState state){
        this.currentState = state;
    }

    public void next(){
        // Delegates the next() call to the current state
        currentState.next(this);
    }

    public String getColor(){
        return currentState.getColor();
    }
}
