package G_Projects.I_Vending_Machine.VendingMachineStates;

import java.util.ArrayList;
import java.util.List;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.PaymentStrategy.PaymentStrategy;
import G_Projects.I_Vending_Machine.UtilityClasses.Inventory;
import G_Projects.I_Vending_Machine.VendingMachineStates.ConcreteStates.IdleState;

public class VendingMachineContext {
    
    private VendingMachineState currMachineState; //Context owns the state
    private PaymentStrategy paymentStrategy;
    private Inventory inventory;
    private List<Coin> coinList;
    private int selectedItemCode;

    public VendingMachineContext(){
        this.currMachineState = new IdleState();
        this.inventory = new Inventory(10); // Initialize inventory with 10 shelves
        this.coinList = new ArrayList<>();
        System.out.println("Initialized Vending Machine in Idle State");
    } 

    // State management
    public void setState(VendingMachineState newState){
        this.currMachineState = newState;
    }
    public VendingMachineState getCurrMachineState(){
        return currMachineState;
    }
    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
   
    // Events (delegated to current state)
    public void insertCoin(Coin coin){
        currMachineState.insertCoin(this, coin);
    }
    public void selectItem(int codeNumber){
        currMachineState.selectItem(this, codeNumber);
    }
    public void dispense(){
        currMachineState.dispenseItem(this);
    }

    public boolean processPayment(int amount){
        if(paymentStrategy == null){
            throw new IllegalStateException("Payment strategy not set");
        }
        return paymentStrategy.processPayment(amount);
    }
    // Domain operations (used by states)
    public Inventory getInventory(){
        return inventory;
    }

    public List<Coin> getCoinList(){
        return coinList;
    }

    public void addCoin(Coin coin){
        coinList.add(coin);
    }

    public int getBalance(){
        int balance = 0;
        for(Coin coin : coinList){
            balance += coin.getValue();
        }
        return balance;
    }

    public void clearBalance(){
        coinList.clear();
    }

    public int getSelectedItemCode(){
        return selectedItemCode;
    }

    public void setSelectedItemCode(int codeNumber){
        this.selectedItemCode = codeNumber;
    }

    public void resetSelection(){
        this.selectedItemCode = 0;
    }
}