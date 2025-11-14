package G_Projects.F_Elevator_System.UtilityClasses;

public class Building {
    private String name;
    private int numberOfFloors;
    private ElevatorController elevatorController; //Controller to manage elevators in the building

    public Building(String name, int numberOfFloors, int numberOfElevators){ 
        this.name = name;
        this.numberOfFloors = numberOfFloors;
        this.elevatorController = new ElevatorController(numberOfElevators, numberOfFloors);
    }

    public String getName(){
        return name;
    }
    public int getNumberOfFloors(){
        return numberOfFloors;
    }
    public ElevatorController getElevatorController(){
        return elevatorController;
    }
}
