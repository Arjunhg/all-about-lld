package G_Projects.G_Inventory_Management_System.ProductReplenishmentStrategies.ConcreteReplenishmentStrategies;

import G_Projects.G_Inventory_Management_System.ProductFactory.Product;
import G_Projects.G_Inventory_Management_System.ProductReplenishmentStrategies.ReplenishmentStrategy;

public class BulkOrderStrategy implements ReplenishmentStrategy {
    @Override
    public void replenish(Product product){
        System.out.println("Replenishing product " + product.getName() + " using Bulk Order Strategy.");
    }
}
