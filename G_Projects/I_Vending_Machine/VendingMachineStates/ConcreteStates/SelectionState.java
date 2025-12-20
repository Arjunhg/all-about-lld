package G_Projects.I_Vending_Machine.VendingMachineStates.ConcreteStates;

// This state is not required anymore as it was causing user to enter the item code twice.

/*
 *
 * Welcome to the Selection State!
 * Here are the possible actions you can perform:
 *
 * 1. Choose the product you wish to purchase.
 * 2. Cancel the selected product if you change your mind.
 * 3. Request a refund if you decide not to proceed.
 * 4. Get change after purchasing your product.
 *
 * Please proceed with your desired action.
 */


/* 
public class SelectionState implements VendingMachineState{
    @Override
    public String getStateName(){
        return "Selection State";
    }

    @Override
    public void insertCoin(VendingMachineContext context, Coin coin){
        context.addCoin(coin);
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
*/