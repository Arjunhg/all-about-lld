package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_Without_Hook.Concrete_Beverages;

import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_Without_Hook.Beverage;

public class Coffee extends Beverage {
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
