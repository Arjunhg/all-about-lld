package B_OPPS.Bb_Object;

import B_OPPS.Ba_Classes.Car;

public class Object {
    public static void main(String[] args){
        // Create a new Car object
        Car myCar = new Car("Red", "Toyota", 2020);
        
        // Display the car details
        myCar.displayDetails();
    }
}