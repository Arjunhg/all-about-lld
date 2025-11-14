package G_Projects.F_Elevator_System;

import java.util.List;
import java.util.Scanner;

import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.ObserverPattern.ElevatorDisplay;
import G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies.FCFSSchedulingStrategy;
import G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies.LookSchedulingStrategy;
import G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies.ScanSchedulingStrategy;
import G_Projects.F_Elevator_System.UtilityClasses.Building;
import G_Projects.F_Elevator_System.UtilityClasses.Elevator;
import G_Projects.F_Elevator_System.UtilityClasses.ElevatorController;

public class Main {
    public static void main(String[] args){

        Building building = new Building("Skyline Tower", 10, 3);
        ElevatorController controller = building.getElevatorController();

        // Observer to display elevator status
        ElevatorDisplay display = new ElevatorDisplay();
        for(Elevator elevator : controller.getElevators()){
            elevator.addObserver(display);
        }

        // Elevator requests using CLI
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Elevator System CLI! Type 'exit' to quit.");
        System.out.println("Building: " + building.getName() + ", Floors: " + building.getNumberOfFloors() + ", Elevators: " + controller.getElevators().size());

        while(running){
            System.out.println("Select an option (numeric):");
            System.out.println("1. Request Elevator (External)");
            System.out.println("2. Request Floor (Internal)");
            System.out.println("3. Simulate next step");
            System.out.println("4. Change Scheduling Strategy");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch(choice){
                case 1: 
                    // Handle external elevator request
                    System.out.print("Enter elevator ID: ");
                    int extElevatorId = scanner.nextInt();
                    controller.setCurrentElevatorId(extElevatorId);
                    System.out.print("Enter floor number: ");
                    int extFloorNumber = scanner.nextInt();
                    System.out.print("Enter direction (1 for UP or 2 for DOWN): ");
                    int dirInput = scanner.nextInt();
                    Direction dir = dirInput == 1 ? Direction.UP : Direction.DOWN;
                    controller.requestElevator(extElevatorId, extFloorNumber, dir);
                    break;
                
                case 2:
                    // Handle internal floor request
                    System.out.print("Enter elevator ID: ");
                    int intElevatorId = scanner.nextInt();
                    controller.setCurrentElevatorId(intElevatorId);
                    System.out.print("Enter destination floor number: ");
                    int intFloorNumber = scanner.nextInt();
                    controller.requestFloor(intElevatorId, intFloorNumber);
                    break;

                case 3:
                    System.out.println("Simulating next step...");
                    controller.step();
                    displayElevatorStatus(controller.getElevators());
                    break;

                case 4:
                    System.out.println("Select Scheduling Strategy:");
                    System.out.println("1. First-Come-First-Serve (FCFS)");
                    System.out.println("2. (SCAN)");
                    System.out.println("3. (LOOK)");
                    int strategyChoice = scanner.nextInt();
                    switch(strategyChoice){
                        case 1:
                            controller.setSchedulingStrategy(new FCFSSchedulingStrategy());
                            System.out.println("Scheduling Strategy set to FCFS");
                            break;
                        case 2:
                            controller.setSchedulingStrategy(new ScanSchedulingStrategy());
                            System.out.println("Scheduling Strategy set to SCAN");
                            break;
                        case 3:
                            controller.setSchedulingStrategy(new LookSchedulingStrategy());
                            System.out.println("Scheduling Strategy set to LOOK");
                            break;
                    }

                case 5:
                    running = false;
                    System.out.println("Exiting Elevator System CLI. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Elevator System CLI terminated.");
    }

    // Display status of all elevators
    private static void displayElevatorStatus(List<Elevator> elevators){
        System.out.println("Elevator Status:");
        for(Elevator elevator : elevators){
            System.out.println("Elevator " +elevator.getElevatorId() + " - Floor: " + elevator.getCurrentFloor() + ", State: " + elevator.getState() + ", Destination: " + elevator.getDestinationFloors());
        }
    }
}
