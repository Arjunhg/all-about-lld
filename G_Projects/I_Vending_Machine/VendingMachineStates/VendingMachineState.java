package G_Projects.I_Vending_Machine.VendingMachineStates;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;

public interface VendingMachineState {
    String getStateName();

    // VendingMachineState next(VendingMachineContext context); : Issue => Can cause circular dependecy and is more of context driven instead of event driven which is not how state pattern should be implemented.

    void insertCoin(VendingMachineContext context, Coin coin);
    void selectItem(VendingMachineContext context, int itemCode);
    void dispenseItem(VendingMachineContext context);
}

/*
- Demonstrates polymorphism in handling state transitions.
- Encapsulates state-specific behavior within each state class.
- Prevents if/else explosion inside the context class.
- Maintains the right level of abstraction for state management.
*/
