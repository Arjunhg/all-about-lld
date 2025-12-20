package G_Projects.I_Vending_Machine.VendingMachineStates.ConcreteStates;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.CommonExceptions.InvalidShelfCodeException;
import G_Projects.I_Vending_Machine.CommonExceptions.ItemSoldOutException;
import G_Projects.I_Vending_Machine.UtilityClasses.Item;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineContext;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineState;

public class HasMoneyState implements VendingMachineState {

    @Override
    public String getStateName(){
        return "Has Money State";
    }

    @Override
    public void insertCoin(VendingMachineContext context, Coin coin){
        context.addCoin(coin); //Allow multiple coins
    }

    @Override
    public void selectItem(VendingMachineContext context, int itemCode){
        Item item;

        try {
            item = context.getInventory().getItem(itemCode);
        } catch (InvalidShelfCodeException e) {
            // Invalid code - this is user error, stay in current state
            throw new IllegalArgumentException("Invalid item code selected: " + itemCode + e);
        } catch (ItemSoldOutException e) {
            // Sold out - transition to OutOfStockState
            context.setState(new OutOfStockState());
            throw new IllegalStateException("Selected item is out of stock: " + e);
        }

        boolean paid = context.processPayment(item.getPrice());
        if(!paid){
            throw new IllegalStateException("Insufficient payment. Please insert more coins or choose a different payment method.");
        }

        context.setSelectedItemCode(itemCode);
        context.setState(new DispenseState());
    }

    @Override
    public void dispenseItem(VendingMachineContext context){
        throw new IllegalStateException("Select item before dispensing");
    }
}