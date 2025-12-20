package G_Projects.I_Vending_Machine.VendingMachineStates.ConcreteStates;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineContext;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineState;

public class OutOfStockState implements VendingMachineState {

    @Override
    public String getStateName() {
        return "Out Of Stock State";
    }

    @Override
    public void insertCoin(VendingMachineContext context, Coin coin) {
        throw new IllegalStateException("Machine is out of stock");
    }

    @Override
    public void selectItem(VendingMachineContext context, int codeNumber) {
        throw new IllegalStateException("Machine is out of stock");
    }

    @Override
    public void dispenseItem(VendingMachineContext context) {
        throw new IllegalStateException("Machine is out of stock");
    }
}
