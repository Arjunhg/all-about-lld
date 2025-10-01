package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.A_NotFollowed.ConcreteClass;

public class Tea {
    public void prepare(){
        boilWater();
        brewTea();
        pourInCup();
        addLemon();
    }

    private void boilWater(){
        System.out.println("Boiling Water");
    }
    private void brewTea(){
        System.out.println("Brewing Tea");
    }
    private void pourInCup(){
        System.out.println("Pouring into cup");
    }
    private void addLemon(){
        System.out.println("Adding Lemon");
    }
}
