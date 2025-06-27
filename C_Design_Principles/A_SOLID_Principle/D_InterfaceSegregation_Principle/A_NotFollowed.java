package C_Design_Principles.A_SOLID_Principle.D_InterfaceSegregation_Principle;

// Example: Violation of the Interface Segregation Principle (ISP)
//
// - The Machine interface defines multiple functionalities: print, scan, and fax.
// - All device classes implementing Machine must provide implementations for all methods.
// - BasicPrinter is a device that only supports printing.
// - BasicPrinter is forced to implement scan() and fax(), which are not relevant to its functionality.
// - This leads to:
//     1. Unnecessary code in BasicPrinter for unsupported operations.
//     2. Potential runtime errors if unsupported methods are called.
//     3. Reduced code clarity and maintainability.
// - This design violates ISP, which states that clients should not be forced to depend on interfaces they do not use.

interface Machine {
    void print();
    void scan();
    void fax();
}

class AllInOnePrinter implements Machine {
    @Override
    public void print(){
        System.out.println("Printing document...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning document...");
    }

    @Override
    public void fax() {
        System.out.println("Faxing document...");
    }
}

class BasicPrinter implements Machine {
    @Override
    public void print() {
        System.out.println("Printing document...");
    }

    // The following methods are not applicable for BasicPrinter, leading to a violation of the Interface Segregation Principle.
    @Override
    public void scan() {
        throw new UnsupportedOperationException("BasicPrinter does not support scanning.");
    }

    @Override
    public void fax() {
        throw new UnsupportedOperationException("BasicPrinter does not support faxing.");
    }
}


public class A_NotFollowed {
    public static void main(String[] args) {
        Machine machine = new BasicPrinter();
        machine.print();
        try {
            machine.scan(); // This will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}
