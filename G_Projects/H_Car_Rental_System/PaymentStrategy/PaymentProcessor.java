package PaymentStrategy;

public class PaymentProcessor {
    public boolean processPayment(PaymentStrategy strategy, double amount){
        strategy.processPayment(amount);
        return true;
    }
}
