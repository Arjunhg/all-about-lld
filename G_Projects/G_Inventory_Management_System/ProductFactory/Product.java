package G_Projects.G_Inventory_Management_System.ProductFactory;

import G_Projects.G_Inventory_Management_System.CommonEnums.ProductEnum;

/*
 * Abstract Product Class - Key Features:
 * • Serves as the foundation for all product types in the inventory system
 * • Contains essential product attributes: SKU, name, price, quantity, and category
 * • Acts as a parent class that specific product implementations will extend
 * • Provides a common interface for managing different product varieties
 * • Ensures consistency across all product types through shared properties
 */

public abstract class Product{
    private String id;
    private String name;
    private double price;
    private int quantity;
    private int threshold;
    private ProductEnum category;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public ProductEnum getCategory() {
        return category;
    }
    public void setCategory(ProductEnum category) {
        this.category = category;
    }
}
