package C_Design_Principles.A_SOLID_Principle.A_SingleResponsibility_Principle;

class BreadBaker {
    public void bakeBread() {
        System.out.println("Baking bread...");
    }
}

class InventoryManager {
    public void manageInventory() {
        System.out.println("Managing inventory...");
    }
}

class CustomerService {
    public void serveCustomer() {
        System.out.println("Serving customer...");
    }
}

class BakeryCleaner {
    public void cleanBakery() {
        System.out.println("Cleaning bakery...");
    }
}

public class B_Followed {
    public static void main(String[] args) {
        BreadBaker baker = new BreadBaker();
        baker.bakeBread();

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.manageInventory();

        CustomerService customerService = new CustomerService();
        customerService.serveCustomer();

        BakeryCleaner cleaner = new BakeryCleaner();
        cleaner.cleanBakery();

        // This design follows the Single Responsibility Principle
        // by separating the responsibilities into distinct classes:
        // BreadBaker for baking bread, InventoryManager for managing inventory,
        // CustomerService for serving customers, and BakeryCleaner for cleaning the bakery.    
    }
}
