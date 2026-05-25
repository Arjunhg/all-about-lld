from CommandPattern.ConcreteClasses.elevator_request import ElevatorRequest
from CommonEnums.direction import Direction
from SchedulingStrategy.ConcreteStrategies.scan_scheduling_strategy import ScanSchedulingStrategy
from UtilityClasses.elevator import Elevator
from UtilityClasses.floor import Floor


class ElevatorController:
    def __init__(self, number_of_elevators: int, number_of_floors: int):
        self.elevators = []
        self.floors = []
        self.scheduling_strategy = ScanSchedulingStrategy()
        self.current_elevator_id = 0

        for index in range(number_of_elevators):
            self.elevators.append(Elevator(index))
        for index in range(number_of_floors):
            self.floors.append(Floor(index))

    def set_scheduling_strategy(self, strategy):
        self.scheduling_strategy = strategy

    def request_elevator(self, elevator_id: int, floor_number: int, direction: Direction):
        direction_name = getattr(direction, "name", str(direction))
        print(
            f"External request: Requesting elevator {elevator_id} "
            f"to floor {floor_number} going {direction_name}"
        )
        selected_elevator = self._get_elevator_by_id(elevator_id)
        if selected_elevator is not None:
            selected_elevator.add_request(
                ElevatorRequest(elevator_id, floor_number, False, direction)
            )
        else:
            print(f"ElevatorController: No elevator found with ID {elevator_id}")

    def request_floor(self, elevator_id: int, floor_number: int):
        selected_elevator = self._get_elevator_by_id(elevator_id)
        if selected_elevator is None:
            print(f"ElevatorController: No elevator found with ID {elevator_id}")
            return
        print(
            f"ElevatorController, Internal request: Elevator {elevator_id} "
            f"requested to go to floor {floor_number}"
        )

        direction = (
            Direction.UP
            if floor_number > selected_elevator.get_current_floor()
            else Direction.DOWN
        )
        selected_elevator.add_request(
            ElevatorRequest(elevator_id, floor_number, True, direction)
        )

    def _get_elevator_by_id(self, elevator_id: int):
        for elevator in self.elevators:
            if elevator.get_elevator_id() == elevator_id:
                return elevator
        return None

    def step(self):
        for elevator in self.elevators:
            if elevator.get_request_queue():
                next_stop = self.scheduling_strategy.get_next_stop(elevator)
                if elevator.get_current_floor() != next_stop:
                    elevator.move_to_next_stop(next_stop)
                else:
                    elevator.complete_request()

    def get_elevators(self):
        return self.elevators

    def get_floors(self):
        return self.floors

    def set_current_elevator_id(self, elevator_id: int):
        self.current_elevator_id = elevator_id
