from CommandPattern.elevator_command import ElevatorCommand
from CommonEnums.direction import Direction


class ElevatorRequest(ElevatorCommand):
    def __init__(
        self,
        elevator_id: int,
        floor: int,
        is_internal: bool,
        direction: Direction,
        elevator_controller=None,
    ):
        self.elevator_id = elevator_id
        self.floor = floor
        self.is_internal_request = is_internal
        self.request_direction = direction
        self.elevator_controller = elevator_controller

    def execute(self):
        if self.elevator_controller is None:
            return
        if self.is_internal_request:
            self.elevator_controller.request_floor(self.elevator_id, self.floor)
        else:
            self.elevator_controller.request_elevator(
                self.elevator_id, self.floor, self.request_direction
            )

    def get_direction(self) -> Direction:
        return self.request_direction

    def check_is_internal_request(self) -> bool:
        return self.is_internal_request

    def get_floor(self) -> int:
        return self.floor

    def __eq__(self, other) -> bool:
        if not isinstance(other, ElevatorRequest):
            return False
        return (
            self.elevator_id == other.elevator_id
            and self.floor == other.floor
            and self.is_internal_request == other.is_internal_request
            and self.request_direction == other.request_direction
        )

    def __hash__(self) -> int:
        return hash(
            (self.elevator_id, self.floor, self.is_internal_request, self.request_direction)
        )
