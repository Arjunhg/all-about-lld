package G_Projects.I_Vending_Machine;

import java.util.Map;
import java.util.Scanner;

import G_Projects.I_Vending_Machine.CommonEnums.Coin;
import G_Projects.I_Vending_Machine.CommonEnums.ItemType;
import G_Projects.I_Vending_Machine.PaymentStrategy.ConcreteStrategy.CardPaymentStrategy;
import G_Projects.I_Vending_Machine.PaymentStrategy.ConcreteStrategy.CoinPaymentStrategy;
import G_Projects.I_Vending_Machine.UtilityClasses.Item;
import G_Projects.I_Vending_Machine.UtilityClasses.ItemShelf;
import G_Projects.I_Vending_Machine.VendingMachineStates.VendingMachineContext;

public class Main {
    public static void main(String[] args) {
        
        VendingMachineContext vendingMachine = new VendingMachineContext();
        boolean initialized = false;
        while(true){
            try {

            System.out.println("|");
            System.out.println("Vending Machine is ready for operations");
            System.out.println("|");

            if(!initialized){
                populateInventory(vendingMachine);
                initialized = true;
            }
            showInventory(vendingMachine);

            Scanner scanner = new Scanner(System.in);

            System.out.println("|");
            System.out.println("Select Payment Method: ");
            System.out.println("1. Coin Payment");
            System.out.println("2. Card Payment");
            System.out.println("Enter your choice: ");

            int paymentChoice = scanner.nextInt();
            scanner.nextLine();

            switch (paymentChoice) {
                case 1:
                    System.out.println("Using Coin Payment Method");
                    vendingMachine.setPaymentStrategy(
                        new CoinPaymentStrategy(vendingMachine.getCoinList())
                    );

                    // Insert coin via EVENT
                    vendingMachine.insertCoin(Coin.TEN_RUPEE);
                    vendingMachine.insertCoin(Coin.TWO_RUPEE);
                    break;

                case 2:
                    System.out.println("Using Card Payment Method");
                    System.out.println("Enter card number: ");
                    String cardNumber =scanner.nextLine();

                    System.out.println("Enter expiry date (MM/YY): ");
                    String expiryDate = scanner.nextLine();

                    System.out.println("Enter CVV: ");
                    String cvv = scanner.nextLine();

                    vendingMachine.setPaymentStrategy(
                        new CardPaymentStrategy(cardNumber, expiryDate, cvv)
                    );
                    break;

                default:
                    System.out.println("Invalid payment choice. Exiting.");
                    scanner.close();
                    break;
            }

            System.out.println("|");
            System.out.println("Enter item code to select item: ");
            int itemCode = scanner.nextInt();

            vendingMachine.selectItem(itemCode);
            vendingMachine.dispense();

            showInventory(vendingMachine);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            showInventory(vendingMachine);
        }
        }
    }

    private static void populateInventory(VendingMachineContext vendingMachine){
        for(int i=0; i<10; i++){
            Item newItem = new Item();
            int codeNumber = 101 +i;

            // Set item type and price based on index detail
            if(i >=0 && i< 3){
                newItem.setType(ItemType.COKE);
                newItem.setPrice(12);
            } else if (i >= 3 && i < 5){
                newItem.setType(ItemType.PEPSI);
                newItem.setPrice(9);
            } else if (i >= 5 && i < 8){
                newItem.setType(ItemType.SODA);
                newItem.setPrice(15);
            } else {
                newItem.setType(ItemType.JUICE);
                newItem.setPrice(10);
            }

            for(int j=0; j<5; j++){
                // Adding 5 items per shelf
                vendingMachine.getInventory().addItem(newItem, codeNumber);
            }
        }
    }

    private static void showInventory(VendingMachineContext vendingMachine){

        System.out.println("Current Inventory:");
        Map<Integer, ItemShelf> slots = vendingMachine.getInventory().getInventory();
        
        for(Map.Entry<Integer, ItemShelf> entry : slots.entrySet()){
            int code = entry.getKey();
            ItemShelf shelf = entry.getValue();

            if(shelf.isSoldOut()){
                System.out.println("Code: " + code + " | SOLD OUT");
            } else {
                Item item = shelf.getItems().get(0);
                System.out.println(
                    "Code: " + code +
                    " | Item: " + item.getType() +
                    " | Price: " + item.getPrice() +
                    " | Quantity: " + shelf.getItems().size()
                );
            }
        }
    }
} 
