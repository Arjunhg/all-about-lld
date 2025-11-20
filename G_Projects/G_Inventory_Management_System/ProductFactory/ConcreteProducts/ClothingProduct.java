package G_Projects.G_Inventory_Management_System.ProductFactory.ConcreteProducts;

import G_Projects.G_Inventory_Management_System.CommonEnums.ProductEnum;
import G_Projects.G_Inventory_Management_System.ProductFactory.Product;

public class ClothingProduct extends Product{
    private String size;
    private String color;

    public ClothingProduct(String id, String name, double price, int quantity, int threshold){
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setThreshold(threshold);
        setCategory(ProductEnum.CLOTHING);
    }

    private String getSize(){
        return size;
    }
    private void setSize(String size){
        this.size = size;
    }

    private String getColor(){
        return color;
    }
    private void setColor(String color){
        this.color = color;
    }
}
