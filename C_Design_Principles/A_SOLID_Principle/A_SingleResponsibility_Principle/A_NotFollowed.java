package C_Design_Principles.A_SOLID_Principle.A_SingleResponsibility_Principle;

// Class with multiple responsibilities.
class BreadBaker {
    public void bakeBread(){
        System.out.println("Baking bread...");
    }

    public void manageInventory(){
        System.out.println("Managing inventory...");
    }

    public void serverCustomer(){
        System.out.println("Serving customer...");
    }

    public void cleanBakery(){
        System.out.println("Cleaning bakery...");
    }
}
public class A_NotFollowed {
    public static void main(String[] args) {
        BreadBaker baker = new BreadBaker();
        baker.bakeBread();
        baker.manageInventory();
        baker.serverCustomer();
        baker.cleanBakery();

        // This class violates the Single Responsibility Principle
        // because it handles multiple responsibilities:
        // baking bread, managing inventory, serving customers,
        // and cleaning the bakery, when the bakers job must be to just bake. Each of these responsibilities
        // should ideally be handled by separate classes to promote
        // better maintainability and separation of concerns.
    }
}
