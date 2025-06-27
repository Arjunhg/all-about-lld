package C_Design_Principles.A_SOLID_Principle.D_InterfaceSegregation_Principle;

// This code demonstrates adherence to the Interface Segregation Principle (ISP):
// Key Points:
// 1. Interfaces are split into smaller, more focused contracts:
//    - Printer: defines print().
//    - Scanner: defines scan().
//    - Fax: defines fax().
// 2. Each class implements only the interfaces relevant to its functionality:
//    - BasicPrinter implements only Printer.
//    - AllInOnePrinter implements Printer, Scanner, and Fax.
// 3. Benefits of this approach:
//    - Classes are not forced to implement unused methods.
//    - Code is more robust, maintainable, and easier to extend.
//    - Promotes cleaner and more understandable design.

interface Printer {
    void print();
}
interface Scanner {
    void scan();
}
interface Fax {
    void fax();
}

class BasicPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("Printing document...");
    }
}

class AllInOnePrinter implements Printer, Scanner, Fax {
    @Override
    public void print() {
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

