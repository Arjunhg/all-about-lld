package B_OPPS.Bd_Static;

public class StaticMethods {
    private int value = 42;

    /* Can't create a constructor with static method due to no 'this' reference
    public static StaticMethods(String brand){ 
        this.brand = brand;
    }
    */

    //Static method
    public static void Example(){
        // this.value = 100; // Error: Cannot use 'this' in static context. Static methods in java have no 'this' reference because static is associated with class not object. And 'this' keyword belongs to object level
        System.out.println("Static method called");
    }

    //Instance method
    public void Example2(){
        this.value = 100; //works fine
        System.out.println("Instance method called");
    }

    public static void main(String[] args) {
        // Calling static method
        StaticMethods.Example();

        // Creating an instance of the class to call the instance method
        StaticMethods instance = new StaticMethods();
        instance.Example2();

        // Accessing the instance variable
        System.out.println("Value: " + instance.value);
    }

}