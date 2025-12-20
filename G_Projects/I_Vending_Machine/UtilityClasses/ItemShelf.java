package G_Projects.I_Vending_Machine.UtilityClasses;

import java.util.ArrayList;
import java.util.List;

public class ItemShelf {

    private int code;
    private List<Item> items;
    // private boolean isSoldOut; // It's better to derive this instead of storing it.

    public ItemShelf(int code){
        this.code = code;
        this.items = new ArrayList<>();
    }

    public boolean isSoldOut(){
        return items.isEmpty();
    }

    /*Dangerours:
     - External code can put shelf in inconsistent state
     - Shelf should control it's own state

        public void setSoldOut(boolean status){
            this.isSoldOut = status;
        }

        public void setItems(List<Item> items){
            this.items = items;
            if(isSoldOut) setSoldOut(false);
        }
    */

    public List<Item> getItems(){
        return new ArrayList<>(items);
    }

    public int getCode(){
        return code;
    }

    public void addItem(Item item){
        items.add(item);
        // if(isSoldOut) setSoldOut(false); // No need to check, derived property
    }

    public void removeItem(Item item){
        items.remove(item);
        // if(items.isEmpty()) setSoldOut(true);
    }
}