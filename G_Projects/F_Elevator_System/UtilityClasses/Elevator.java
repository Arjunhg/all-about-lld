package G_Projects.F_Elevator_System.UtilityClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import G_Projects.F_Elevator_System.CommandPattern.ConcreteClasses.ElevatorRequest;
import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.CommonEnums.ElevatorState;
import G_Projects.F_Elevator_System.ObserverPattern.ElevatorObserver;

// Represent individual elevators in the system
/*
 * 🏢 ELEVATOR CLASS OVERVIEW
 * 
 * ⚙️  CORE FUNCTIONALITIES:
 * • Manages individual elevator operations and state transitions
 * • Handles elevator movement between floors with direction control
 * • Processes and queues floor requests from users
 * • Maintains current position and operational status
 * 
 * 🔄 OBSERVER PATTERN IMPLEMENTATION:
 * • Notifies registered observers about elevator state changes
 * • Broadcasts floor change events to all listening components
 * • Enables real-time monitoring and system coordination
 * • Supports multiple observers for different system components
 * 
 * 🎯 KEY RESPONSIBILITIES:
 * • State management (IDLE, MOVING, STOPPED)
 * • Request queue processing and validation
 * • Floor-to-floor movement coordination
 * • Real-time event notification system
 */

public class Elevator {
    private int id; //unique identifier for each elevator
    private int currentFloor;
    private Direction direction;
    private ElevatorState state; // idle, moving, doors open/closed etc

    private List<ElevatorObserver> observers; //List of observers to notify on state changes
    private Queue<ElevatorRequest> requests; // Queue of requests assigned to this elevator for each floor(up/down)

    public Elevator(int id){
        this.id = id;
        this.currentFloor = 0; // assuming ground floor as starting point
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.observers = new ArrayList<>();
        this.requests = new LinkedList<>();
    }

    // Observer to monitor elevator state changes
    public void addObserver(ElevatorObserver observer){
        observers.add(observer);
    }
    public void removeObserver(ElevatorObserver observer){
        observers.remove(observer);
    }

    // Notify all observers about state change
    public void notifyStateChange(){
        for(ElevatorObserver observer : observers){
            observer.onElevatorStateChange(this, state);
        }
    }
    public void notifyFloorChange(int floor){
        for(ElevatorObserver observer : observers){
            observer.onElevatorFloorChange(this, floor);
        }
    }

    public Direction getDirection(){
        return direction;
    }
    public void setDirection(Direction newDirection){
        this.direction = newDirection;
    }

    // Set new state for elevator and notify observers
    public void setState(ElevatorState newState){
        this.state = newState;
        notifyStateChange();
    }

    // Add new floor request to queue
    public void addRequest(ElevatorRequest request){
        // Avoid duplicate requests
        if(!requests.contains(request)){
            requests.add(request);
        }

        int requestedFloor = request.getFloor();

        if(state==ElevatorState.IDLE && request!=null && !requests.isEmpty()){
            if(requestedFloor > currentFloor){
                setDirection(Direction.UP);
            } else if(requestedFloor < currentFloor){
                setDirection(Direction.DOWN);
            }
            setState(ElevatorState.MOVING);
        }
    }

    // Move elevator to next stop decided by scheduling algorithm
    public void moveToNextStop(int nextStop){
        if(state != ElevatorState.MOVING){
            return; // Elevator not in moving state. Only move if it's moving
        }

        while(currentFloor != nextStop){
            if(direction == Direction.UP){
                currentFloor++;
            } else if(direction == Direction.DOWN){
                currentFloor--;
            }
            notifyFloorChange(currentFloor);
            if(currentFloor==nextStop){
                completeArrival();
                return;
            }
        }
    }

    // What happens when elevator reaches a requested floor?
    private void completeArrival(){
        // Stop elevator and notify observers
        setState(ElevatorState.STOPPED);
        // Remove current floor request from queue
        requests.removeIf(request -> request.getFloor() == currentFloor);
        // if no more requests, set to idle
        if(requests.isEmpty()){
            setDirection(Direction.IDLE);
            setState(ElevatorState.IDLE);
        } else {
            setState(ElevatorState.MOVING);
        }
    }

    public int getElevatorId(){
        return id;
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public ElevatorState getState(){
        return state;
    }
    public Queue<ElevatorRequest> getRequestQueue(){
        return new LinkedList<>(requests);
    }
    /*
     * 🔒 ENCAPSULATION PROTECTION: Why We Return a Defensive Copy
     * 
     * ❌ PROBLEM: Direct Reference Return
     * • If we return the actual queue reference, external code can modify it directly
     * • This breaks encapsulation and violates the principle of data hiding
     * • Example of dangerous approach:
     *   public Queue<ElevatorRequest> getRequestsQueue() {
     *       return requests; // DANGEROUS! Direct reference exposed
     *   }
     * 
     * 💥 WHAT CAN GO WRONG:
     * • External code gets direct access: Queue<ElevatorRequest> queue = elevator.getRequestsQueue();
     * • They can clear all requests: queue.clear(); // Oops! All pending requests gone!
     * • They can add invalid requests: queue.add(illegalRequest); // Bypasses validation!
     * • They can reorder requests inappropriately
     * 
     * ✅ SOLUTION: Defensive Copy Pattern
     * • We return a new LinkedList copy of our internal queue
     * • External code gets their own copy to work with
     * • Our original data remains protected and unchanged
     * • Example of safe approach:
     *   public Queue<ElevatorRequest> getRequestsQueue() {
     *       return new LinkedList<>(requests); // Safe defensive copy
     *   }
     * 
     * 🛡️ HOW IT PROTECTS US:
     * • External modifications only affect the copy: queue.clear(); // Only clears their copy
     * • Original queue stays intact and functional
     * • Elevator maintains control over its internal state
     * • Data integrity is preserved
     */

     public List<ElevatorRequest> getDestinationFloors(){
        return new ArrayList<>(requests);
     }
}
