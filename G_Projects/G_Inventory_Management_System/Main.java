package G_Projects.G_Inventory_Management_System;

import G_Projects.G_Inventory_Management_System.CommonEnums.ProductEnum;
import G_Projects.G_Inventory_Management_System.InventoryStockManager.InventoryManager;
import G_Projects.G_Inventory_Management_System.ProductFactory.Product;
import G_Projects.G_Inventory_Management_System.ProductFactory.ProductFactory;
import G_Projects.G_Inventory_Management_System.ProductReplenishmentStrategies.ReplenishmentStrategy;
import G_Projects.G_Inventory_Management_System.ProductReplenishmentStrategies.ConcreteReplenishmentStrategies.BulkOrderStrategy;
import G_Projects.G_Inventory_Management_System.ProductReplenishmentStrategies.ConcreteReplenishmentStrategies.JustInTimeStrategy;
import G_Projects.G_Inventory_Management_System.UtilityClasses.Warehouse;

public class Main {
    public static void main(String[] args) {

        ReplenishmentStrategy replenishmentStrategy = new JustInTimeStrategy();
        InventoryManager inventoryManager = InventoryManager.getInstance(replenishmentStrategy);
        
        ProductFactory product = new ProductFactory();
        Product laptop = product.createProduct(ProductEnum.ELECTRONICS, "L1", "Laptop", 100.0, 50, 25);
        Product tShirt = product.createProduct(ProductEnum.CLOTHING, "TS1", "T-Shirt", 20.0, 200, 100);
        Product apple = product.createProduct(ProductEnum.GROCERY, "A1", "Apple", 1.0, 100, 200);

        Warehouse w1 = new Warehouse("Warehouse 1");
        Warehouse w2 = new Warehouse("Warehouse 2");
        inventoryManager.addWarehouse(w1);
        inventoryManager.addWarehouse(w2);

        w1.addProduct(laptop, 15);
        w1.addProduct(tShirt, 20);
        w2.addProduct(apple, 50);

        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());

        inventoryManager.performInventoryCheck();

        inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());

        inventoryManager.checkAndReplenishStock("L1");

        
    }
}
