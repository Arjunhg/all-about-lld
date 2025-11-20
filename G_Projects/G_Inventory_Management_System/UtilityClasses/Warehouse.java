package G_Projects.G_Inventory_Management_System.UtilityClasses;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import G_Projects.G_Inventory_Management_System.ProductFactory.Product;

public class Warehouse {
    private String name;
    private String location;
    
    private Map<String, Product> products;

    public Warehouse(String name){
        this.name = name;
        this.products = new HashMap<>();
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void addProduct(Product product, int quantity){
        String id = product.getId();
        if(products.containsKey(id)){
            // Product already exists, update quantity
            Product existingProduct = products.get(id);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        }else{
            product.setQuantity(quantity); 
        }
        products.put(id, product);
        System.out.println("Added " + quantity + " of product ID: " + id + " to warehouse: " + name + ". New quantity: " + getAvailableQuantity(id));
    }

    // remove product from warehouse
    public boolean removeProduct(String id, int quantity){
        if(products.containsKey(id)){
            Product product = products.get(id);
            int currQuantity = product.getQuantity();
            if(currQuantity >= quantity){
                // safe to remove
                product.setQuantity(currQuantity - quantity);
                System.out.println("Removed " + quantity + " of product ID: " + id + " from warehouse: " + name + ". Remaining Quantity: " + product.getQuantity());

                if(product.getQuantity() == 0){
                    products.remove(id);
                    System.out.println("Product ID: " + id + " is out of stock and removed from warehouse: " + name);
                }
                return true;
            }else{
                System.out.println("Insufficient stock for product ID: " + id + " in warehouse: " + name + ". Available: " + currQuantity + ", Requested: " + quantity);
                return false;
            }
        }else{
            System.out.println("Product ID: " + id + " not found in warehouse: " + name);
            return false;
        }
    }

    public int getAvailableQuantity(String id){
        if(products.containsKey(id)){
            return products.get(id).getQuantity();
        }
        return 0;
    }

    public Product getProductByID(String id){
        return products.getOrDefault(id, null);
    }

    public Collection<Product> getAllProducts(){
        return products.values();
    }
}
