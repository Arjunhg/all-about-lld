from CommonEnums.direction import Direction
from SchedulingStrategy.scheduling_strategy import SchedulingStrategy


class LookSchedulingStrategy(SchedulingStrategy):
    def get_next_stop(self, elevator) -> int:
        current_floor = elevator.get_current_floor()
        requests = elevator.get_request_queue()

        if not requests:
            return current_floor

        next_request = requests[0]
        next_floor = next_request.get_floor()

        if next_floor > current_floor:
            travel_direction = Direction.UP
        elif next_floor < current_floor:
            travel_direction = Direction.DOWN
        else:
            return current_floor

        candidate = None
        for req in requests:
            req_floor = req.get_floor()
            if travel_direction == Direction.UP and current_floor < req_floor <= next_floor:
                if req.check_is_internal_request() or (
                    not req.check_is_internal_request() and req.get_direction() == Direction.UP
                ):
                    if candidate is None or req_floor < candidate:
                        candidate = req_floor
            elif travel_direction == Direction.DOWN and next_floor <= req_floor < current_floor:
                if req.check_is_internal_request() or (
                    not req.check_is_internal_request()
                    and req.get_direction() == Direction.DOWN
                ):
                    if candidate is None or candidate < req_floor:
                        candidate = req_floor

        return candidate if candidate is not None else next_floor
