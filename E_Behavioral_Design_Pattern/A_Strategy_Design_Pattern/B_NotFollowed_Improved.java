package E_Behavioral_Design_Pattern.A_Strategy_Design_Pattern;

interface PaymentMethod {
    void processPayment();
}

class CreditCardPayment implements PaymentMethod {
    public void processPayment(){
        System.out.println("Processing credit card payment.");
    }
}

class PayPalPayment implements PaymentMethod {
    public void processPayment(){
        System.out.println("Processing PayPal payment.");
    }
}

class StripePayment implements PaymentMethod {
    public void processPayment(){
        System.out.println("Processing Stripe payment.");
    }
}
class UnknownPayment implements PaymentMethod {
    public void processPayment(){
        System.out.println("Unknown payment type.");
    }
}


class PaymentProcessor {
    public void processPayment(String paymentMethod) {
        if (paymentMethod.equals("CreditCard")) {
            CreditCardPayment creditCard = new CreditCardPayment();
            creditCard.processPayment(); 
        } else if (paymentMethod.equals("PayPal")) {
            PayPalPayment payPal = new PayPalPayment();
            payPal.processPayment(); 
        } else if (paymentMethod.equals("Stripe")) {
            StripePayment stripe = new StripePayment();
            stripe.processPayment(); 
        } else {
            System.out.println("Payment method not supported.");
        }
    }
}


public class B_NotFollowed_Improved {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.processPayment("paypal");
        paymentProcessor.processPayment("creditcard");
    }
}