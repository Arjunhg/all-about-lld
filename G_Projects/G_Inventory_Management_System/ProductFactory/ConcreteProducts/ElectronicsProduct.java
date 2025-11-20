package G_Projects.G_Inventory_Management_System.ProductFactory.ConcreteProducts;

import G_Projects.G_Inventory_Management_System.CommonEnums.ProductEnum;
import G_Projects.G_Inventory_Management_System.ProductFactory.Product;

public class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyPeriod; 

    public ElectronicsProduct(String id, String name, double price, int quantity, int threshold) {
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setThreshold(threshold);
        setCategory(ProductEnum.ELECTRONICS);
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
