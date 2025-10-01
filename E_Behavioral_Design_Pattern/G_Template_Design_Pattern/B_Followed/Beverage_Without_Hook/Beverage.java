package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_Without_Hook;

public abstract class Beverage{
    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public abstract void brew();
    public abstract void addCondiments();

    void boilWater(){
        System.out.println("Boiling Water");
    }
    void pourInCup(){
        System.out.println("Pouring into cup");
    }
}