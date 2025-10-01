package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_With_Hook;

public abstract class BeverageWithHook {
    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()){
            addCondiments();
        }
    }

    void boilWater(){
        System.out.println("Boiling Water");
    }

    void pourInCup(){
        System.out.println("Pouring into cup");
    }

    public abstract void brew();

    public abstract void addCondiments();

    public boolean customerWantsCondiments(){
        return true;
    }
}
