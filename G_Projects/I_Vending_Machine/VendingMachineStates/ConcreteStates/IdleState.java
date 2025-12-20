package G_Projects.I_Vending_Machine.VendingMachineStates.ConcreteStates;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineContext;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineState;

public class IdleState implements VendingMachineState {

    @Override
    public String getStateName(){
        return "Idle State";
    }

    @Override
    public void insertCoin(VendingMachineContext context, Coin coin){
        context.addCoin(coin);
        context.setState(new HasMoneyState());
    }

    @Override
    public void selectItem(VendingMachineContext context, int itemCode){
        throw new IllegalStateException("Insert money first");
    }

    @Override
    public void dispenseItem(VendingMachineContext context){
        throw new IllegalStateException("Nothing to dispense in Idle State");
    }
}