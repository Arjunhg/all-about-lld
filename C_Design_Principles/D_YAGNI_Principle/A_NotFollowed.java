package C_Design_Principles.D_YAGNI_Principle;

/*
 * Example Scenario: Violating the YAGNI Principle
 *
 * Interview Task:
 * - Design a PaymentProcessor class.
 * - Requirements:
 *     • Support only debit and credit card payments.
 *     • Focus on current requirements; avoid unnecessary complexity.
 *
 * Mistake:
 * - Implementing support for PayPal and cryptocurrency payments, assuming future need.
 *
 * Why This Violates YAGNI:
 * 1. Adds unnecessary complexity not requested by the problem statement.
 * 2. Wastes time on features that are not currently required.
 * 3. Can negatively impact your interview by not following instructions.
 */

class PaymentProcessor {
    private String paymentMethod;
    PaymentProcessor(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    void processPayment(double amount) {
        if(paymentMethod.equalsIgnoreCase("CreditCard")){
            System.out.println("Processing payment of $" + amount + " using Credit Card.");
        }else if(paymentMethod.equalsIgnoreCase("DebitCard")){
            System.out.println("Processing payment of $" + amount + " using Debit Card.");
        } else if(paymentMethod.equalsIgnoreCase("PayPal")){
            // Unnecessary complexity for current requirements
            System.out.println("Processing payment of $" + amount + " using PayPal.");
        } else if(paymentMethod.equalsIgnoreCase("CryptoCurrency")){
            // Unnecessary complexity for current requirements
            System.out.println("Processing payment of $" + amount + " using Cryptocurrency.");
        } else {
            System.out.println("Unsupported payment method: " + paymentMethod);
        }
    }
}

public class A_NotFollowed {
    public static void main(String[] args) {
        
        PaymentProcessor processor = new PaymentProcessor("CreditCard");
        processor.processPayment(100.0); // Processing payment of $100.0 using Credit Card.

        PaymentProcessor invalidProcessor = new PaymentProcessor("CryptoCurrency");
        invalidProcessor.processPayment(50.0); // Processing payment of $50.0 using Cryptocurrency.
    }
}
