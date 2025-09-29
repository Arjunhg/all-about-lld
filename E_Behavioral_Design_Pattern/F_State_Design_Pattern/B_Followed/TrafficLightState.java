package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed;

/*
 * 🚦 State Design Pattern Solution - Traffic Light System
 * 
 * ❌ Problem with Traditional Approach:
 * • Creates chaotic, hard-to-maintain code
 * • Difficult to add new states or modify existing ones
 * • Violates Open/Closed Principle
 * 
 * ✅ State Design Pattern Benefits:
 * • Separates state-specific behavior into individual classes
 * • Allows objects to change behavior when internal state changes
 * • Promotes cleaner, more maintainable code
 * • Enables better scalability and extensibility
 * 
 * 🛠️ How We're Solving It:
 * • Create separate state classes for each traffic light color
 * • Each state class handles its own transition logic
 * • Delegate state-specific behaviors to appropriate classes
 * • Achieve better organization and maintainability
 * 
 * 📋 Implementation Strategy:
 * 1. Define a State interface/abstract class
 * 2. Create concrete state classes (Red, Yellow, Green)
 * 3. Implement state-specific behavior in each class
 * 4. Handle state transitions cleanly
 */

public interface TrafficLightState {
    void next(TrafficLightContext context); //Transition to next state
    String getColor(); //Returns current state color
}