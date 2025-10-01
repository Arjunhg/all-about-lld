package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_With_Hook.Concrete_Beverages_With_Hook;

import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_With_Hook.BeverageWithHook;

public class CustomCoffee extends BeverageWithHook {
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    public boolean customerWantsCondiments() {
        return false; // Customer does not want condiments
    }
}
