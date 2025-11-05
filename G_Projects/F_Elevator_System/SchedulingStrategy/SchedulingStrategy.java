package G_Projects.F_Elevator_System.SchedulingStrategy;

import G_Projects.F_Elevator_System.UtilityClasses.Elevator;

public interface SchedulingStrategy {
    // Determine the next stop for the given elevator based on the scheduling strategy
    int getNextStop(Elevator elevator);
}
