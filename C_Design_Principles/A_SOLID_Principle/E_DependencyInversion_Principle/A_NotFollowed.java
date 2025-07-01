package C_Design_Principles.A_SOLID_Principle.E_DependencyInversion_Principle;

// Example: Consider an enterprise e-commerce system where order processing requires various types of notifications to be sent to customers, administrators, and inventory systems

/*
 * Issues with this design:
 * OrderService directly depends on EmailNotifier, DatabaseLogger, and InventorySystem
 * 1. Tight coupling: OrderService is bound to specific implementations.
 * 2. Hard to test: Concrete dependencies make unit testing difficult.
 * 3. Inflexible: Any change in notification/logging requires modifying OrderService.
 * 4. Poor maintainability: Tight coupling complicates maintenance and updates.
 * 5. Limited extensibility: Difficult to support different notification strategies.
 */

class EmailNotifier {
    public void sendEmail(String message){
        System.out.println("Sending email: " + message);
    }
}

class DatabaseLogger {
    public void log(String message) {
        System.out.println("Logging to database: " + message);
    }
}

class InventorySystem {
    public void updateInventory(String orderDetails) {
        System.out.println("Updating inventory for: " + orderDetails);
    }
}

class OrderService {
    private EmailNotifier emailNotifier;
    private DatabaseLogger databaseLogger;
    private InventorySystem inventorySystem;

    public OrderService(EmailNotifier emailNotifier, DatabaseLogger databaseLogger, InventorySystem inventorySystem) {
        this.emailNotifier = emailNotifier;
        this.databaseLogger = databaseLogger;
        this.inventorySystem = inventorySystem;
    }

    public void processOrder(String orderDetails) {
        // Process the order
        System.out.println("Processing order: " + orderDetails);
        
        // Notify customer
        emailNotifier.sendEmail("Your order has been processed: " + orderDetails);
        
        // Log the order
        databaseLogger.log("Order processed: " + orderDetails);
        
        // Update inventory
        inventorySystem.updateInventory(orderDetails);
    }
}

/*
 * Why is tight coupling and relying on concrete implementations a problem?
 *
 * 1. Tight coupling to specific implementations:
 *    - OrderService is written to use EmailNotifier, DatabaseLogger, and InventorySystem directly.
 *    - If you want to use a different notification method (like SMS), you must change OrderService code.
 *    - This makes it hard to swap out or add new behaviors without editing OrderService itself.
 *
 *    Analogy: Imagine a pizza shop where the delivery person only knows how to drive a specific car model. If you want to switch to a bike or scooter, you have to retrain them from scratch. If they just knew how to "drive any vehicle," you could swap vehicles easily.
 *
 * 2. Difficulty in unit testing due to concrete dependencies:
 *    - OrderService expects real EmailNotifier, DatabaseLogger, and InventorySystem objects.
 *    - In tests, you might want to use fake or mock versions (that don't actually send emails or update inventory), but you can't easily do this.
 *
 *    Analogy: If your pizza oven is bolted to the floor, you can't replace it with a test oven for practice.
 *
 * 3. Inflexibility when business requirements change:
 *    - If you want to add SMS notifications or change how logging works, you must modify OrderService.
 *    - This makes the system hard to extend for new requirements or markets.
 *
 *    Analogy: If your chef only knows how to make pizza, you can't ask for pasta without retraining them. If they know how to "cook Italian food," you can ask for anything.
 *
 * 4. Challenges in maintaining and modifying the system:
 *    - Every change in notification or logging logic requires changes in OrderService.
 *    - This increases the risk of bugs and makes maintenance harder.
 *
 * 5. Difficulty in implementing different notification strategies for different markets or customer segments:
 *    - The system can't easily support different notification methods for different users.
 *    - You'd have to keep adding more code to OrderService, making it messy and hard to manage.
 *
 * What does "relying on concrete implementation" mean?
 *    - It means OrderService is written to use specific classes (EmailNotifier, DatabaseLogger, InventorySystem), not just any class that can "notify" or "log".
 *    - If you used interfaces (like Notifier, Logger), OrderService could work with any implementation, making it flexible and testable.
 *
 * Summary Table:
 * | Problem         | Why it happens                 | Analogy                                 |
 * |-----------------|-------------------------------|------------------------------------------|
 * | Hard to change  | Code expects specific classes | Delivery only with Brand X car           |
 * | Hard to test    | Can't swap in test versions   | Oven is bolted, can't use test oven      |
 * | Inflexible      | Can't add new types easily    | Chef only knows pizza, not pasta         |
 *
 * Solution:
 *    - Use interfaces/abstractions (e.g., Notifier, Logger) so OrderService can work with any notifier or logger, making it flexible, testable, and easy to change.
 */