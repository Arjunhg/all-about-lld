package G_Projects.F_Elevator_System.UtilityClasses;

import java.util.ArrayList;
import java.util.List;

import G_Projects.F_Elevator_System.CommandPattern.ConcreteClasses.ElevatorRequest;
import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.SchedulingStrategy.SchedulingStrategy;
import G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies.ScanSchedulingStrategy;

// Manages and coordinates multiple elevators within the building
/**
 * ElevatorController - Central Management System
 * 
 * Key Responsibilities:
 * • Manages multiple elevators within a building system
 * • Initializes and coordinates elevator fleet operations
 * • Processes external elevator requests from floors
 * • Handles internal floor requests from elevator passengers
 * • Implements configurable scheduling strategies for optimal routing
 * • Simulates real-time elevator movement and positioning
 * • Provides centralized control for building-wide elevator operations
 */
public class ElevatorController {
    private List<Elevator> elevators; //List of elevators in the system
    private List<Floor> floors;
    private SchedulingStrategy schedulingStrategy; // Strategy for assigning requests to elevators
    private int currentElevatorId;

    // To define number of elevators and floors in the system
    public ElevatorController(int numberOfElevators, int numberOfFloors) {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.schedulingStrategy = new ScanSchedulingStrategy();
        for(int i=0; i<numberOfElevators; i++){
            elevators.add(new Elevator(i));
        }
        for(int i=0; i<numberOfFloors; i++){
            floors.add(new Floor(i));
        }
    }

    // dynamically set scheduling strategy
    public void setSchedulingStrategy(SchedulingStrategy strategy){
        this.schedulingStrategy = strategy;
    }

    // Handle external elevator request
    public void requestElevator(int elevatorId, int floorNumber, Direction direction){
        System.out.println("External request: Requesting elevator " + elevatorId + " to floor " + floorNumber + " going " + direction);

        Elevator selectedElevator = getElevatorById(elevatorId);

        if(selectedElevator != null){
            // Add request to it
            selectedElevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, false, direction));
        }else{
            System.out.println("ElevatorController: No elevator found with ID " + elevatorId);
        }
    }

    // Handle internal elevator request
    public void requestFloor(int elevatorId, int floorNumber){
        Elevator selectedElevator = getElevatorById(elevatorId);
        System.out.println("ElevatorController, Internal request: Elevator " + elevatorId + " requested to go to floor " + floorNumber);

        Direction direction = floorNumber > selectedElevator.getCurrentFloor() ? Direction.UP : Direction.DOWN;

        // add request to elevator
        selectedElevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, false, direction));
    }

    private Elevator getElevatorById(int id){
        for(Elevator elevator : elevators){
           if(elevator.getElevatorId()==id){
                return elevator;
           }
        }
        return null;
    }

    public void step(){
        for(Elevator elevator : elevators){
            if(!elevator.getRequestQueue().isEmpty()){
                // use scheduling strategy to get next stop
                int nextStop = schedulingStrategy.getNextStop(elevator);

                // move elevator to next stop
                if(elevator.getCurrentFloor() != nextStop){
                    elevator.moveToNextStop(nextStop);
                }
            }
        }
    }

    public List<Elevator> getElevators(){
        return elevators;    
    }

    public List<Floor> getFloors(){
        return floors;
    }

    public void setCurrentElevatorId(int id){
        this.currentElevatorId = id;
    }
}
