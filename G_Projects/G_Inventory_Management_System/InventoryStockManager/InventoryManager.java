package G_Projects.G_Inventory_Management_System.InventoryStockManager;

import java.util.ArrayList;
import java.util.List;

import G_Projects.G_Inventory_Management_System.ProductFactory.Product;
import G_Projects.G_Inventory_Management_System.ProductFactory.ProductFactory;
import G_Projects.G_Inventory_Management_System.ProductReplenishmentStrategies.ReplenishmentStrategy;
import G_Projects.G_Inventory_Management_System.UtilityClasses.Warehouse;

public class InventoryManager {
    private static InventoryManager instance;

    private List<Warehouse> warehouses; 
    private ProductFactory productFactory;
    private ReplenishmentStrategy replenishmentStrategy;

    private InventoryManager(ReplenishmentStrategy replenishmentStrategy){
        warehouses = new ArrayList<>();;
        productFactory = new ProductFactory();
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy){
        if(instance == null){
            instance = new InventoryManager(replenishmentStrategy);
        }
        return instance;
    }

    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy){
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public void addWarehouse(Warehouse warehouse){
        warehouses.add(warehouse);
    }
    public void removeWarehouse(Warehouse warehouse){
        warehouses.remove(warehouse);
    }

    public Product getProductByID(String id){
        for(Warehouse warehouse : warehouses){
            Product product = warehouse.getProductByID(id);
            if(product != null){
                return product;
            }
        }
        return null;
    }

    public void checkAndReplenishStock(String id){
        Product product = getProductByID(id);
        if(product != null){
            if(product.getQuantity() < product.getThreshold()){
                // Trigger current replenishment strategy
                if(replenishmentStrategy != null){
                    replenishmentStrategy.replenish(product);
                }
            }
        }
    }

    public void performInventoryCheck(){
        for(Warehouse warehouse : warehouses){
            for(Product product : warehouse.getAllProducts()){
                if(product.getQuantity() < product.getThreshold()){
                    if(replenishmentStrategy != null){
                        replenishmentStrategy.replenish(product);
                    }
                }
            }
        }
    }

}
