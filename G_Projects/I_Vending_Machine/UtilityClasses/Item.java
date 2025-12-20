package G_Projects.I_Vending_Machine.UtilityClasses;

import G_Projects.I_Vending_Machine.CommonEnums.ItemType;

public class Item {
    // private String id; //Should we keep an id?
    private ItemType type;
    private int price;

    public ItemType getType(){
        return type;
    }
    public void setType(ItemType type){
        this.type = type;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
}