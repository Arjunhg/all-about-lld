package G_Projects.F_Elevator_System.SchedulingStrategy.ConcreteStrategies;

import java.util.PriorityQueue;
import java.util.Queue;

import G_Projects.F_Elevator_System.CommandPattern.ConcreteClasses.ElevatorRequest;
import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.SchedulingStrategy.SchedulingStrategy;
import G_Projects.F_Elevator_System.UtilityClasses.Elevator;

public class ScanSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator){

        // Current floor and direction of elevator
        Direction elevatorDirection = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();

        Queue<ElevatorRequest> requests = elevator.getRequestQueue();

        if(requests.isEmpty()){
            elevator.setDirection(Direction.IDLE);
            return currentFloor;
        }

        // Priority Queue for handling direction in up and down direction
        PriorityQueue<ElevatorRequest> upQueue = new PriorityQueue<>();
        PriorityQueue<ElevatorRequest> downQueue = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());
        /*
         * Other ways:
            1) new Comaparator<ElevatorRequest>(){
                    @Override
                    public int compare(ElevatorRequest a, ElevatorRequest b){
                        return a.getFloor() - b.getFloor();
                    }
                }

            2) Comparator Class
                Comparator<ElevatorRequest> byFloorAsc = Comparator.comparingInt(ElevatorRequest::getFloor);
                PriorityQueue<ElevatorRequest> downQueue = new PriorityQueue<>(byFloorAsc);

            3) (a, b) -> Integer.compare(a.getFloor(), b.getFloor())

         */

        // Request distribution based on their relative position to current floor
        while(!requests.isEmpty()){
            ElevatorRequest elevatorRequest = requests.poll();
            int floor = elevatorRequest.getFloor();
            if(floor > currentFloor){
                upQueue.offer(elevatorRequest);
            }else{
                downQueue.offer(elevatorRequest);
            }
        }

        // What if Elevator is idle?
        if(elevatorDirection == Direction.IDLE){
            // Get the nearest request and set the direction based on it
            int nearestUpRequest = upQueue.isEmpty() ? -1 : upQueue.peek().getFloor();
            int nearestDownRequest = downQueue.isEmpty() ? -1 : downQueue.peek().getFloor();

            if(nearestUpRequest == -1){
                elevator.setDirection(Direction.DOWN);
                return downQueue.poll().getFloor();
            }else if(nearestDownRequest == -1){
                elevator.setDirection(Direction.UP);
                return upQueue.poll().getFloor();
            }else{
                // pick the nearest one
                if(nearestUpRequest - currentFloor < currentFloor - nearestDownRequest){ //Valid only for proper SCAN algo, for mixed or unsorted queues use Math.abs()
                    elevator.setDirection(Direction.UP);
                    return upQueue.poll().getFloor();
                }else{
                    elevator.setDirection(Direction.DOWN);
                    return downQueue.poll().getFloor();
                }
            }
        }

        // Movement in up direction
        if(elevatorDirection == Direction.UP){
            return !upQueue.isEmpty() ? upQueue.poll().getFloor() : switchDirection(elevator, downQueue);
        }else{
            return !downQueue.isEmpty() ? downQueue.poll().getFloor() : switchDirection(elevator, upQueue);
        }
    }
    private int switchDirection(Elevator elevator, Queue<ElevatorRequest> oppositeQueue){
        elevator.setDirection(elevator.getDirection() == Direction.UP ? Direction.DOWN : Direction.UP);
        return oppositeQueue.isEmpty() ? elevator.getCurrentFloor() : oppositeQueue.poll().getFloor();
    }
}
