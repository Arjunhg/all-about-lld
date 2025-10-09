package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed.A_Concrete_Devices;

public class CoffeeMachine {
    
    public void initialize(){
        System.out.println("CoffeeMachine initialized");
    }

    public void brewCoffee(){
        System.out.println("CoffeeMachine is brewing coffee");
    }

    public void stopBrewing(){
        System.out.println("CoffeeMachine has stopped brewing");
    }

    public void shutdown(){
        System.out.println("CoffeeMachine shutting down");
    }
}
