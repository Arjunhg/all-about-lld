package G_Projects.F_Elevator_System.ObserverPattern;

import G_Projects.F_Elevator_System.CommonEnums.ElevatorState;
import G_Projects.F_Elevator_System.UtilityClasses.Elevator;

public interface ElevatorObserver {
    void onElevatorStateChange(Elevator elevator, ElevatorState state); //But elevator already has state info, so do we need it? Refer to LawOfDemeter.md
    void onElevatorFloorChange(Elevator elevator, int floor);
}
