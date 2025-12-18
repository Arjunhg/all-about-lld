package PaymentStrategy;

// Strategy Pattern for Payments:
//
// - Want to efficiently manage parking payments for different vehicles?
// - Let's use the Strategy pattern for user payments!
// - This approach allows us to handle various payment methods flexibly and cleanly.

public interface PaymentStrategy {
    void processPayment(double amount);
}
