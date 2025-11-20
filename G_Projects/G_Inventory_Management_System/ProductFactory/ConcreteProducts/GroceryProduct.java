package G_Projects.G_Inventory_Management_System.ProductFactory.ConcreteProducts;

import G_Projects.G_Inventory_Management_System.CommonEnums.ProductEnum;
import G_Projects.G_Inventory_Management_System.ProductFactory.Product;
import java.util.Date;

public class GroceryProduct extends Product{
    private Date expiryDate;
    private boolean refrigerated;

    public GroceryProduct(String id, String name, double price, int quantity, int threshold){
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setThreshold(threshold);
        setCategory(ProductEnum.GROCERY);
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }
    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }
}
