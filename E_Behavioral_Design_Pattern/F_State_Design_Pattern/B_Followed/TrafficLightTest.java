package E_Behavioral_Design_Pattern.F_State_Design_Pattern.B_Followed;

public class TrafficLightTest {
    public static void main(String[] args) {
        
        TrafficLightContext trafficLight = new TrafficLightContext();
        // Simulate state tansitions by calling next()
        trafficLight.next();
        trafficLight.next();
        trafficLight.next();
        trafficLight.next();
        trafficLight.next();
    }
}
