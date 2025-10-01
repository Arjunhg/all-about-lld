package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_With_Hook;

import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_With_Hook.Concrete_Beverages_With_Hook.CustomCoffee;

public class BeverageWithHookDemo {
    public static void main(String[] args) {
        BeverageWithHook coffee = new CustomCoffee();
        System.out.println("\nMaking Custom Coffee...");
        coffee.prepareRecipe();
    }
}
