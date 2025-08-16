package E_Behavioral_Design_Pattern.A_Strategy_Design_Pattern;

class PaymentProcessor {
    public void processPayment(String type){
        if(type.equals("CreditCard")){
            System.out.println("Processing credit card payment.");
        } else if(type.equals("PayPal")){
            System.out.println("Processing PayPal payment.");
        } else if(type.equals("Stripe")){
            System.out.println("Processing Stripe payment.");
        } else {
            System.out.println("Unknown payment type.");
        }
    }
}

public class A_NotFollowed {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment("CreditCard");
        processor.processPayment("PayPal");
        processor.processPayment("Bitcoin");
    }
}   
    
