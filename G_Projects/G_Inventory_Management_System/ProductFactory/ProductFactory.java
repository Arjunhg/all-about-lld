package G_Projects.G_Inventory_Management_System.ProductFactory;

import G_Projects.G_Inventory_Management_System.CommonEnums.ProductEnum;
import G_Projects.G_Inventory_Management_System.ProductFactory.ConcreteProducts.ClothingProduct;
import G_Projects.G_Inventory_Management_System.ProductFactory.ConcreteProducts.ElectronicsProduct;
import G_Projects.G_Inventory_Management_System.ProductFactory.ConcreteProducts.GroceryProduct;

public class ProductFactory {
    public Product createProduct(ProductEnum category, String id, String name, double price, int quantity, int threshold){
        switch(category){
            case ELECTRONICS:
                return new ElectronicsProduct(id, name, price, quantity, threshold);
            case CLOTHING:
                return new ClothingProduct(id, name, price, quantity, threshold);
            case GROCERY:
                return new GroceryProduct(id, name, price, quantity, threshold);
            default:
                throw new IllegalArgumentException("Unsupported product category: " + category);
        }
    }
}
