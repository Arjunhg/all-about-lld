package B_OPPS.H_Encapsulation;

/*
 * 🔒 ENCAPSULATION - Data Hiding & Controlled Access
 * 
 * 📦 What is Encapsulation?
 * - Bundling data (variables) and methods into a single class
 * - Restricting direct access to object's internal state
 * - Providing controlled access through public methods (getters/setters)
 * 
 * 🎯 Benefits:
 * - Data protection from unauthorized access
 * - Better control over data validation
 * - Easier maintenance and debugging
 * - Flexibility to change internal implementation
 */

class BankAccount {
    // 🔒 Private fields - Cannot be accessed directly from outside
    private String accountNumber;
    private double balance;
    private String accountHolder;
    
    // 🏗️ Constructor - Controlled way to initialize object
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        // ✅ Validation during initialization
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("⚠️ Initial balance cannot be negative. Set to 0.");
        }
    }
    
    // 🔍 Getter methods - Read-only access to private data
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // 🔧 Setter with validation - Controlled modification
    public void setAccountHolder(String accountHolder) {
        if (accountHolder != null && !accountHolder.trim().isEmpty()) {
            this.accountHolder = accountHolder;
        } else {
            System.out.println("❌ Invalid account holder name!");
        }
    }
    
    // 💰 Business methods with built-in validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited: $" + amount + " | New Balance: $" + balance);
        } else {
            System.out.println("❌ Deposit amount must be positive!");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn: $" + amount + " | New Balance: $" + balance);
        } else if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be positive!");
        } else {
            System.out.println("❌ Insufficient funds! Available: $" + balance);
        }
    }
    
    // 📊 Method to display account info (controlled access)
    public void displayAccountInfo() {
        System.out.println("🏦 Account: " + accountNumber + 
                          " | Holder: " + accountHolder + 
                          " | Balance: $" + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("🔒 Demonstrating Encapsulation\n");
        
        // ✅ Creating account through constructor (controlled initialization)
        BankAccount account = new BankAccount("ACC123", "John Doe", 1000.0);
        
        // 🔍 Accessing data through getters (read-only access)
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getAccountHolder());
        System.out.println("Current Balance: $" + account.getBalance());
        System.out.println();
        
        // 💰 Using business methods (controlled operations)
        account.deposit(500);
        account.withdraw(200);
        account.withdraw(2000); // This will fail - insufficient funds
        System.out.println();
        
        // 🔧 Using setter with validation
        account.setAccountHolder("Jane Smith");
        account.setAccountHolder(""); // This will fail - invalid name
        System.out.println();
        
        // 📊 Display final account state
        account.displayAccountInfo();
        
        System.out.println("\n🎯 Key Points:");
        System.out.println("- Direct access to balance, accountNumber blocked");
        System.out.println("- All modifications go through validated methods");
        System.out.println("- Data integrity maintained through encapsulation");
        
        // ❌ These would cause compile errors (demonstrating encapsulation):
        // account.balance = -1000; // Cannot access private field
        // account.accountNumber = "HACKED"; // Cannot access private field
    }
}