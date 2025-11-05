package G_Projects.F_Elevator_System.ObserverPattern;

import G_Projects.F_Elevator_System.CommonEnums.ElevatorState;
import G_Projects.F_Elevator_System.UtilityClasses.Elevator;

public class ElevatorDisplay implements ElevatorObserver{
    @Override
    public void onElevatorStateChange(Elevator elevator, ElevatorState state){
        System.out.println("Elevator " + elevator.getElevatorId() + " changed state to " + state);
    }

    @Override
    public void onElevatorFloorChange(Elevator elevator, int floor){
        System.out.println("Elevator " + elevator.getElevatorId() + " arrived at floor " + floor);
    }
}
