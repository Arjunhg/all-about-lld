package E_Behavioral_Design_Pattern.F_State_Design_Pattern.A_NotFollowed;

/*
 * 🚦 TRAFFIC LIGHT SYSTEM - State Design Pattern Example
 * 
 * Let's explore the State Design Pattern through a real-world traffic light system!
 * 
 * 📍 PROBLEM SCENARIO:
 * A traffic light can exist in multiple states, each with specific behaviors:
 * 
 * 🔴 RED STATE:
 *    • Behavior: Cars must STOP
 *    • Next transition: Can change to GREEN
 * 
 * 🟢 GREEN STATE:
 *    • Behavior: Cars can GO
 *    • Next transition: Can change to YELLOW
 * 
 * 🟡 YELLOW STATE:
 *    • Behavior: Cars should SLOW DOWN and prepare to stop
 *    • Next transition: Can change to RED
 * 
 * 📍 CURRENT IMPLEMENTATION: Traditional
 */

/* 
public class TrafficLight {
    private String color;

    public TrafficLight(){
        this.color = "RED";
    }

    public void next(){
        if(color.equals("RED")){
            color = "GREEN";
            System.out.println("Traffic Light changed to GREEN. Cars can GO.");
        } else if(color.equals("GREEN")){
            color = "YELLOW";
            System.out.println("Traffic Light changed to YELLOW. Cars should SLOW DOWN.");
        } else if(color.equals("YELLOW")){
            color = "RED";
            System.out.println("Traffic Light changed to RED. Cars must STOP.");
        }
    }

    public String getColor(){
        return color;
    }
}
*/

/*
 * 🤔 INTERVIEWER QUESTIONS & CHALLENGES:
 * 
 * The interviewer might ask these thought-provoking questions:
 * 
 * 📌 QUESTION 1: Extensibility Challenge
 *    • "What if we add a new state like BLINKING or MAINTENANCE mode?"
 *    • 🔍 Think about: How many places would you need to modify?
 * 
 * 📌 QUESTION 2: Complex Behavior Challenge
 *    • "How would you handle more complex transitions or behaviors based on time or external events?"
 *    • 🔍 Think about: What if each state has different timing requirements?
 * 
 * 📌 QUESTION 3: Open/Closed Principle Challenge
 *    • "Can you easily extend this system without modifying the existing TrafficLight class?"
 *    • 🔍 Think about: Is this code following SOLID principles?
 * 
 * ⚠️ ISSUES WITH CURRENT APPROACH:
 *    • Adding new states requires modifying existing code
 *    • TrafficLight class becomes bloated with state logic
 *    • TrafficLight class is tightly coupled with state management
 *    • Complex if-else chains become harder to maintain
 *    • Violates Open/Closed Principle
 *    • State-specific behavior is scattered throughout the class
 * 
 * 💡 These questions highlight potential issues with the traditional approach,
 *    especially as the system grows in complexity.
 */

public class TrafficLight {
    private String color;

    public TrafficLight(){
        this.color = "RED";
    }

    public void next(){
        if(color.equals("RED")){
            color = "GREEN";
            System.out.println("Traffic Light changed to GREEN. Cars can GO.");
        } else if(color.equals("GREEN")){
            color = "YELLOW";
            System.out.println("Traffic Light changed to YELLOW. Cars should SLOW DOWN.");
        } else if(color.equals("YELLOW")){
            color = "RED";
            System.out.println("Traffic Light changed to RED. Cars must STOP.");
        } else if(color.equals("BLINKING")){
            color = "MAINTENANCE";
            System.out.println("Traffic Light changed to MAINTENANCE.");
        } else if(color.equals("MAINTENANCE")){
            color = "RED";
            System.out.println("Maintenance completed. Traffic Light changed to RED.");
        }
    }

    public String getColor(){
        return color;
    }
}