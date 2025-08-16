package E_Behavioral_Design_Pattern.A_Strategy_Design_Pattern;

/*
 * - The traditional approach has limitations when adding new payment methods.
 * - The Strategy Design Pattern provides a more elegant solution.
 * - In this pattern, we define a family of algorithms (payment methods).
 * - The client (e.g., PaymentProcessor) selects the appropriate algorithm at runtime.
 * - This allows easy addition of new payment methods without modifying existing code.
 */

interface PaymentStrategy {
    void processPayment();
}

// Payment strategies for each payment method
/*
 * - Each class (e.g., CreditCardPayment, PayPalPayment, StripePayment) implements the PaymentStrategy interface.
 * - Each class provides its own implementation of the processPayment() method.
 * - The processPayment() method contains the logic specific to processing that payment method.
 */
class CreditCardPayment implements PaymentStrategy {
    public void processPayment() {
        System.out.println("Processing credit card payment.");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void processPayment() {
        System.out.println("Processing PayPal payment.");
    }
}

class StripePayment implements PaymentStrategy {
    public void processPayment() {
        System.out.println("Processing Stripe payment.");
    }
}

/*
 * - The key idea in the Strategy Pattern is delegation of payment processing to the appropriate strategy.
 * - The PaymentProcessor class holds a reference to a PaymentStrategy.
 * - The PaymentProcessor delegates the call to processPayment() to the selected PaymentStrategy.
 * - This allows changing the payment method at runtime without modifying the PaymentProcessor code.
 */
    
class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void processPayment() {
        paymentStrategy.processPayment();
    }

    // Dynamically change the payment strategy
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
}

public class C_Followed {
    public static void main(String[] args) {
        
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentStrategy payPal = new PayPalPayment();

        PaymentProcessor processor = new PaymentProcessor(creditCard);

        processor.processPayment(); // Processing credit card payment.
        processor.setPaymentStrategy(payPal); // Switch to PayPal
        processor.processPayment(); // Processing PayPal payment.
    }
}

/*
 * Advantages of the Strategy Pattern:
 * 1. Flexibility:
 *    - Switch between different payment strategies at runtime without modifying the PaymentProcessor class.
 *
 * 2. Maintainability:
 *    - Add new payment methods by creating new strategy classes without changing existing code.
 *
 * 3. Separation of Concerns:
 *    - Each payment method has its own class, making the code easier to understand and maintain.
 *
 * 4. Extensibility:
 *    - Easily add new payment methods by creating new strategy classes.
 *
 * Real-Life Use Cases for the Strategy Pattern:
 * - Payment Methods:
 *   - Process payments via different methods like Credit Card, PayPal, Crypto, etc.
 *
 * - Sorting Algorithms:
 *   - Use different sorting strategies (e.g., quick sort, merge sort) depending on the situation.
 *
 * - Shipping Costs:
 *   - Calculate shipping costs based on factors such as location, delivery speed, and package size.
 */
