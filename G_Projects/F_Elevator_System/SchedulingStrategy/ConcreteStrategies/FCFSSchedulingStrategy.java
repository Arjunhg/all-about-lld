package G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies;

import java.util.Queue;

import G_Projects.F_Elevator_System.CommandPattern.ConcreteClasses.ElevatorRequest;
import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.SchedulingStrategy.SchedulingStrategy;
import G_Projects.F_Elevator_System.UtilityClasses.Elevator;

public class FCFSSchedulingStrategy implements SchedulingStrategy{
    @Override
    public int getNextStop(Elevator elevator){
        // Current info of elevator
        // Direction elevatorDirection = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();

        // Retrive the floor list in queue
        Queue<ElevatorRequest> requestQueue = elevator.getRequestQueue();

        // What if the queue is empty? Stay on current floor
        if(requestQueue.isEmpty()){
            elevator.setDirection(Direction.IDLE);
            return currentFloor;
        }

        // Get the next requested floor
        // int nextRequestedFloor = requestQueue.poll().getFloor(); //Remove only when we reach the floor
        ElevatorRequest nextRequest = requestQueue.peek();
        int targetFloor = nextRequest.getFloor();

        /*/
            if(elevatorDirection == Direction.IDLE){
                elevator.setDirection(nextRequestedFloor > currentFloor ? Direction.UP : Direction.DOWN);
            }else if(elevatorDirection == Direction.UP && nextRequestedFloor < currentFloor){
                elevator.setDirection(Direction.DOWN);
            }else if(nextRequestedFloor > currentFloor){
                elevator.setDirection(Direction.UP);
            }
            // It's becoming smart and FCFS is not smart so we keep it pure FCFS
        */
        if(targetFloor > currentFloor){
            elevator.setDirection(Direction.UP);
        }else if(targetFloor < currentFloor){
            elevator.setDirection((Direction.DOWN));
        }else{
            // Same floor, just remove the request
            requestQueue.poll();
            elevator.setDirection(Direction.IDLE);
        }

        return targetFloor;
    }
}
