package G_Projects.I_Vending_Machine.PaymentStrategy.ConcreteStrategy;

import java.util.List;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.PaymentStrategy.PaymentStrategy;

public class CoinPaymentStrategy implements PaymentStrategy {
    private List<Coin> insertedCoins;

    public CoinPaymentStrategy(List<Coin> insertedCoins){
        this.insertedCoins = insertedCoins;
    }
    @Override
    public boolean processPayment(int amount){
        int total = 0;
        for(Coin coin : insertedCoins){
            total += coin.getValue();
        }
        return total >= amount; // Check if the total value of inserted coins is greater then what needs to be paid
    }
}
