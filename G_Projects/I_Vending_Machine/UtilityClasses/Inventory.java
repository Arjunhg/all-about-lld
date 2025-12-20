package G_Projects.I_Vending_Machine.UtilityClasses;

import java.util.HashMap;
import java.util.Map;

import G_Projects.I_Vending_Machine.CommonExceptions.InvalidShelfCodeException;
import G_Projects.I_Vending_Machine.CommonExceptions.ItemSoldOutException;

public class Inventory {
    
    // private ItemShelf[] inventory = null;

    /*
        public Inventory(int size){
            inventory = new ItemShelf[size];
            initializeEmptyInventory();
        }
    */

    /*
        public ItemShelf[] getInventory(){
            /*return inventory; 
            - We should not expose the internal array directly.
            - Caller can modify the shelves directly and break the encapsulation
            
            return inventory.clone();
        }
    */
     
    /*
        public void setInventory(ItemShelf[] inventory){
            this.inventory = inventory;
        }

        - Why it’s not receommended to have a setter for inventory?:
            - Allows replacing entire inventory at runtime
            - Can break machine invariants
            - Rarely needed in domain models
    */

    /*
        public void initializeEmptyInventory(){
            int startCode = 101;
            for(int i=0; i<inventory.length; i++){
                inventory[i] = new ItemShelf(startCode++);
            }
        }
    */

    /*
        public void addItem(Item item, int codeNumber){
            for(ItemShelf itemShelf : inventory){
                if(itemShelf.getCode() == codeNumber){
                    itemShelf.addItem(item);
                    return;
                }
            }
        }
    */


    private Map<Integer, ItemShelf> inventory; //since we model shelves by code and not by index

    public Inventory(int numberOfShelves){
        this.inventory = new HashMap<>();
        initializeEmptyInventory(numberOfShelves);
    }

    public Map<Integer, ItemShelf> getInventory(){
        return new HashMap<>(inventory); // Return a copy to preserve encapsulation or use Collections.unmodifiableMap(inventory);
        /* unmodifiableMap is not deeply immutable.
              Caller can still modify the ItemShelf objects inside the map.
              If ItemShelf is mutable then this is still allowed: getInventory().get(101).addItem(...); // allowed

        */
    }

    public void initializeEmptyInventory(int numberOfShelves){
        int startCode = 101;
        for(int i=0; i<numberOfShelves; i++){
            inventory.put(startCode, new ItemShelf(startCode));
            startCode++;
        }
    }  

    public void addItem(Item item, int codeNumber){
        ItemShelf shelf = inventory.get(codeNumber);
        if(shelf == null){
            throw new InvalidShelfCodeException("Invalid shelf code: " + codeNumber);
        }
        shelf.addItem(item);
    }

    public Item getItem(int codeNumber){
        ItemShelf shelf = inventory.get(codeNumber);
        if(shelf == null){
            throw new InvalidShelfCodeException("Invalid shelf code: " + codeNumber);
        }
        if(shelf.isSoldOut()){
            throw new ItemSoldOutException("Shelf is sold out: " + codeNumber);
        }

        return shelf.getItems().get(0);
    }

    public void removeItem(int codeNumber){
        ItemShelf shelf = inventory.get(codeNumber);
        if(shelf==null){
            throw new InvalidShelfCodeException("Invalid shelf code: " + codeNumber);
        }
        if(shelf.isSoldOut()){
            throw new ItemSoldOutException("Cannot remove item. Shelf is sold out: " + codeNumber);
        }

        shelf.removeItem(shelf.getItems().get(0));
    }  
}