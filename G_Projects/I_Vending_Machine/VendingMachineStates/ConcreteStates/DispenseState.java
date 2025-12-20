package G_Projects.I_Vending_Machine.VendingMachineStates.ConcreteStates;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.CommonExceptions.InvalidShelfCodeException;
import G_Projects.I_Vending_Machine.CommonExceptions.ItemSoldOutException;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineContext;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineState;

public class DispenseState implements VendingMachineState{

    @Override
    public String getStateName(){
        return "Dispense State";
    }

    @Override
    public void insertCoin(VendingMachineContext context, Coin coin){
        throw new IllegalStateException("Cannot insert coin while dispensing item");
    }

    @Override
    public void selectItem(VendingMachineContext context, int itemCode){
        throw new IllegalStateException("Cannot select item while dispensing another item");
    }

    @Override
    public void dispenseItem(VendingMachineContext context){

        int code = context.getSelectedItemCode();

        try {
            context.getInventory().removeItem(code);
        } catch (InvalidShelfCodeException e) {
            throw new IllegalStateException("Invalid shelf code during dispense: " + code + e);
        } catch (ItemSoldOutException e) {
            context.setState(new OutOfStockState());
            throw new IllegalStateException("Item sold out during dispense: " + code + e);
        }

        context.clearBalance();
        context.resetSelection();

        context.setState(new IdleState());
    }
}