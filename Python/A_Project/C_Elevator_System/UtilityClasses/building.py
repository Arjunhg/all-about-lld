from UtilityClasses.elevator_controller import ElevatorController


class Building:
    def __init__(self, name: str, number_of_floors: int, number_of_elevators: int):
        self.name = name
        self.number_of_floors = number_of_floors
        self.elevator_controller = ElevatorController(number_of_elevators, number_of_floors)

    def get_name(self) -> str:
        return self.name

    def get_number_of_floors(self) -> int:
        return self.number_of_floors

    def get_elevator_controller(self):
        return self.elevator_controller
