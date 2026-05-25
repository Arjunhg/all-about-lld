from CommonEnums.direction import Direction
from SchedulingStrategy.scheduling_strategy import SchedulingStrategy


class FCFSSchedulingStrategy(SchedulingStrategy):
    def get_next_stop(self, elevator) -> int:
        current_floor = elevator.get_current_floor()
        request_queue = elevator.get_request_queue()

        if not request_queue:
            elevator.set_direction(Direction.IDLE)
            return current_floor

        next_request = request_queue[0]
        target_floor = next_request.get_floor()

        if target_floor > current_floor:
            elevator.set_direction(Direction.UP)
        elif target_floor < current_floor:
            elevator.set_direction(Direction.DOWN)
        else:
            elevator.set_direction(Direction.IDLE)

        return target_floor
