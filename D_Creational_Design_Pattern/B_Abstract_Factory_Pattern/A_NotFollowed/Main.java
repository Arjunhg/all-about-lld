package D_Creational_Design_Pattern.B_Abstract_Factory_Pattern.A_NotFollowed;

public class Main {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Vehicle honda = factory.createVehicle("Honda");
        honda.start();
        honda.stop();
    }
}

// Issues:
// - Car creation logic is duplicated wherever a car is needed
// - Adding a new brand requires updating all such places
// - No abstraction for families of related objects (brands)
// - Hard to maintain and extend

