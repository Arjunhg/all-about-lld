package G_Projects.F_Elevator_System.CommandPattern.ConcreteClasses;

import G_Projects.F_Elevator_System.CommandPattern.ElevatorCommand;
import G_Projects.F_Elevator_System.CommonEnums.Direction;
import G_Projects.F_Elevator_System.UtilityClasses.ElevatorController;

public class ElevatorRequest implements ElevatorCommand {
    private int elevatorId;
    private int floor;
    private Direction requestDirection;
    private ElevatorController elevatorController;
    private boolean isInternalRequest;

    public ElevatorRequest(int elevatorId, int floor, boolean isInternal, Direction direction){
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternal;
        this.requestDirection = direction;
    }

    @Override
    public void execute(){
        if(isInternalRequest){
            elevatorController.requestFloor(elevatorId, floor);;
        }else{
            elevatorController.requestElevator(elevatorId, floor, requestDirection);
        }
    }

    public Direction getDirection(){
        return requestDirection;
    }

    public boolean checkIsInternalRequest(){
        return isInternalRequest;
    }

    public int getFloor(){
        return floor;
    }
}
