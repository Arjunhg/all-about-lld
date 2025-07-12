package C_Design_Principles.D_YAGNI_Principle;

/*
 * Example Scenario: Following the YAGNI Principle
 *
 * Interview Task:
 * - Design a PaymentProcessor class.
 * - Requirements:
 *     • Support only debit and credit card payments.
 *     • Focus on current requirements; avoid unnecessary complexity.
 *
 * Solution:
 * - Only implement support for DebitCard and CreditCard.
 * - No PayPal or cryptocurrency support unless explicitly required.
 */

class PaymentProcessor {
    private String paymentMethod;

    PaymentProcessor(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void processPayment(double amount) {
        if (paymentMethod.equalsIgnoreCase("CreditCard")) {
            System.out.println("Processing payment of $" + amount + " using Credit Card.");
        } else if (paymentMethod.equalsIgnoreCase("DebitCard")) {
            System.out.println("Processing payment of $" + amount + " using Debit Card.");
        } else {
            System.out.println("Unsupported payment method: " + paymentMethod);
        }
    }
}

public class B_Followed {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor("CreditCard");
        processor.processPayment(100.0); // Processing payment of $100.0 using Credit Card.

        PaymentProcessor invalidProcessor = new PaymentProcessor("CryptoCurrency");
        invalidProcessor.processPayment(50.0); // Unsupported payment method: CryptoCurrency
    }
}
