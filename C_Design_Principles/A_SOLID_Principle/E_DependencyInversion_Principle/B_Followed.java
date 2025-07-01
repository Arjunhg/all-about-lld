package C_Design_Principles.A_SOLID_Principle.E_DependencyInversion_Principle;

/*
To adhere to the Dependency Inversion Principle:

1. Introduce abstractions (interfaces or abstract classes) for high-level and low-level modules.
2. Ensure high-level modules depend on abstractions, not concrete implementations.
3. Use dependency injection to provide concrete implementations to classes at runtime.
4. Decouple classes like OrderService from specific implementations, making the code more flexible and testable.
*/

interface NotificationService {
    void sendNotification(String message);
}
interface LoggingService {
    void logMessage(String message);
    void logError(String error);
}

class Product { // public static
        // Product properties and methods
}

class Order {
    // Order properties and methods
    Product getProduct(){
        // Return the product associated with the order
        return new Product();
    }
}

interface InventoryService {

    // class Product { // public static
    //     // Product properties and methods
    // }
    void updateStock(Order order);
    boolean checkAvailibility(Product product);
}

class SMSNotifier implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotifier implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Push Notification: " + message);
    }
}

class DatabaseLogger implements LoggingService {
    @Override
    public void logMessage(String message) {
        System.out.println("Logging message to database: " + message);
    }

    @Override
    public void logError(String error) {
        System.out.println("Logging error to database: " + error);
    }
}

class OrderService {
    private final NotificationService notificationService;
    private final LoggingService loggingService;
    private final InventoryService inventoryService;

    // Constructor injection for dependencies
    public OrderService(NotificationService notificationService, LoggingService loggingService, InventoryService inventoryService) {
        this.notificationService = notificationService;
        this.loggingService = loggingService;
        this.inventoryService = inventoryService;
    }

    public void processOrder(Order order){
        try {
            // check inventory
            if(inventoryService.checkAvailibility(order.getProduct())) {
                // Process order
                inventoryService.updateStock(order);
                // Send notification
                notificationService.sendNotification("Order processed successfully for product: " + order.getProduct());
                // Log the order
                loggingService.logMessage("Order processed: " + order.getProduct());
            }
        } catch (Exception e) {
            // Log error
            loggingService.logError("Error processing order: " + e.getMessage());
            // Optionally, send notification about the error
            notificationService.sendNotification("Failed to process order: " + e.getMessage());
        }
    }
}

/*
How does this implementation achieve Dependency Inversion Principle (DIP) and improve over the previous approach?

1. Uses Abstractions (Interfaces):
   - This code defines interfaces: NotificationService, LoggingService, and InventoryService.
   - OrderService depends on these interfaces, not on specific classes like SMSNotifier or DatabaseLogger.

2. OrderService is Decoupled from Concrete Implementations:
   - OrderService does not care how notifications are sent or how logging is done.
   - You can pass in any class that implements the required interface (SMS, Email, Push, FileLogger, etc.).

3. Flexible and Easily Extensible:
   - Want to add a new notification method (e.g., Email)? Just create a new class that implements NotificationService—no need to change OrderService.
   - Want to change logging to a file instead of a database? Just inject a different implementation.

4. Easier to Test:
   - For unit testing, you can pass in “fake” or “mock” implementations of the interfaces, so you don’t send real notifications or write to a real database.

What can this do that the previous code couldn’t?

| Previous Code (Tightly Coupled)         | Current Code (DIP, Decoupled)                |
|-----------------------------------------|----------------------------------------------|
| Must change OrderService to add/change  | Can add new notification/logging types       |
| notification/logging                    | without touching OrderService                |
| Hard to test (needs real dependencies)  | Easy to test (inject mocks/fakes)            |
| Not flexible for new requirements       | Easily supports new strategies (e.g., SMS,   |
|                                         | Email, Push)                                 |
| Maintenance is hard—every change risks  | Maintenance is easy—change implementations   |
| breaking things                         | without touching business logic              |

Analogy:
- Old Way: Your chef only knows how to make pizza. If you want pasta, you must retrain the chef.
- New Way: Your chef knows how to “cook Italian food” (interface). You can swap in any chef who knows Italian cooking, or even add new dishes, without retraining.

Summary:
- DIP says: “Depend on abstractions, not concretions.”
- The current code follows DIP by making OrderService depend on interfaces, not specific classes.
- This makes your code flexible, testable, and easy to extend—all things the previous code struggled with.
*/

public class B_Followed {
    public static void main(String[] args) {
        // Create instances of concrete implementations
        NotificationService smsNotifier = new SMSNotifier();
        LoggingService databaseLogger = new DatabaseLogger();
        InventoryService inventoryService = new InventoryService() {
            @Override
            public void updateStock(Order order) {
                System.out.println("Updating stock for order: " + order.getProduct());
            }

            @Override
            public boolean checkAvailibility(Product product) {
                // Simulate availability check
                return true; // Assume product is available for simplicity
            }
        };

        // Inject dependencies into OrderService
        OrderService orderService = new OrderService(smsNotifier, databaseLogger, inventoryService);

        // Create an order and process it
        Order order = new Order();
        orderService.processOrder(order);
    }
}