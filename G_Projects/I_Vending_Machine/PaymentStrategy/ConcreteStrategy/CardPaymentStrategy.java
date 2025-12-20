package G_Projects.I_Vending_Machine.PaymentStrategy.ConcreteStrategy;

import G_Projects.I_Vending_Machine.PaymentStrategy.PaymentStrategy;

public class CardPaymentStrategy implements PaymentStrategy {
    
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CardPaymentStrategy(String cardNumber, String expiryDate, String cvv){
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(int amount){
        // Simulate card payment processing
        System.out.println("Processing card payment of amount: " + amount);
        // In real scenario, integrate with payment gateway here
        return true; // Assume payment is always successful for this example
    }
}
