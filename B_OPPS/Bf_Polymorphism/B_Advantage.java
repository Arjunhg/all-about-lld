// package B_OPPS.Bf_Polymorphism;

// /*
//  * Code Reusability
//  * Flexibilty
//  * Extensibility
// */

// interface Vehicle {
//     void start(); //abstract method
// }

// class Car implements Vehicle {
//     @Override
//     public void start(){
//         // super.start(); // This line is not needed as start() is an abstract method in the interface
//         System.out.println("Car is starting");
//     }
// }
// class Bike implements Vehicle {
//     @Override
//     public void start(){
//         System.out.println("Bike is starting");
//     }
// }
// class Truck implements Vehicle {
//     @Override
//     public void start(){
//         System.out.println("Truck is starting");
//     }
// }

// public class Bf_B_Advantage {
//     public static void main(String[] args) {
//         Vehicle[] vehicles = { new Car(), new Bike(), new Truck() };
//         for(Vehicle vehicle : vehicles){
//             vehicle.start(); //Polymorphic
//         }
//     }
// }
