package G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies;

import java.util.Queue;

import G_Projects.F_Elevator_System.CommandPattern.ConcreteClasses.ElevatorRequest;
import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.SchedulingStrategy.SchedulingStrategy;
import G_Projects.F_Elevator_System.UtilityClasses.Elevator;

public class LookSchedulingStrategy implements SchedulingStrategy  {
    @Override
    public int getNextStop(Elevator elevator){

        int currentFloor = elevator.getCurrentFloor();
        // Direction elevatorDirection = elevator.getDirection();
        Queue<ElevatorRequest> requests = elevator.getRequestQueue();

        // What if no requests?
        if(requests==null || requests.isEmpty()) return currentFloor;

        // Get the next target based on first request in the queue
        ElevatorRequest nextRequest = requests.peek();
        int nextFloor = nextRequest.getFloor();

        // Figure direction baed on next request
        Direction travelDirection;
        if(nextFloor > currentFloor){
            travelDirection = Direction.UP;
        }else if(nextFloor < currentFloor){
            travelDirection = Direction.DOWN;
        }else{
            return currentFloor; // Same floor
        }

        // Along the journey request
        Integer candidate = null;

        for(ElevatorRequest req : requests){
            int reqFloor = req.getFloor();

            // check if the req is within bounds of curr floor and next floor
            if(travelDirection == Direction.UP && reqFloor>currentFloor && reqFloor<=nextFloor){
                // for internal request always consider it but for external request only consider if direction matches(UP)
                if(req.checkIsInternalRequest() || (!req.checkIsInternalRequest() && req.getDirection()==Direction.UP)){
                    // choose smallest floor greater than current floor
                    if(candidate == null || reqFloor < candidate){
                        candidate = reqFloor;
                    }
                }
            }else if(travelDirection == Direction.DOWN && reqFloor < currentFloor && reqFloor>=nextFloor){
                if(req.checkIsInternalRequest() || (!req.checkIsInternalRequest() && req.getDirection() == Direction.DOWN)){
                    if(candidate==null || candidate<reqFloor){
                        candidate = reqFloor;
                    }
                }
            }
        }

        // If candidate was found in path then return it as the next stop, else continue with original target (next floor)
        return (candidate != null) ? candidate : nextFloor;
    }
}
