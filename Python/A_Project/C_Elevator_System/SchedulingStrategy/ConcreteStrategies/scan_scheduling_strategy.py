import heapq

from CommonEnums.direction import Direction
from SchedulingStrategy.scheduling_strategy import SchedulingStrategy


class ScanSchedulingStrategy(SchedulingStrategy):
    def get_next_stop(self, elevator) -> int:
        elevator_direction = elevator.get_direction()
        current_floor = elevator.get_current_floor()

        requests = elevator.get_request_queue()
        if not requests:
            elevator.set_direction(Direction.IDLE)
            return current_floor

        up_queue = []
        down_queue = []

        for req in requests:
            floor = req.get_floor()
            if floor > current_floor:
                heapq.heappush(up_queue, floor)
            else:
                heapq.heappush(down_queue, -floor)

        if elevator_direction == Direction.IDLE:
            nearest_up = up_queue[0] if up_queue else None
            nearest_down = -down_queue[0] if down_queue else None

            if nearest_up is None:
                elevator.set_direction(Direction.DOWN)
                return nearest_down if nearest_down is not None else current_floor
            if nearest_down is None:
                elevator.set_direction(Direction.UP)
                return nearest_up

            if nearest_up - current_floor < current_floor - nearest_down:
                elevator.set_direction(Direction.UP)
                return heapq.heappop(up_queue)
            elevator.set_direction(Direction.DOWN)
            return -heapq.heappop(down_queue)

        if elevator_direction == Direction.UP:
            if up_queue:
                return heapq.heappop(up_queue)
            return self._switch_direction(elevator, down_queue)

        if down_queue:
            return -heapq.heappop(down_queue)
        return self._switch_direction(elevator, up_queue)

    def _switch_direction(self, elevator, opposite_queue) -> int:
        elevator.set_direction(
            Direction.DOWN if elevator.get_direction() == Direction.UP else Direction.UP
        )
        if not opposite_queue:
            return elevator.get_current_floor()
        return (
            -heapq.heappop(opposite_queue)
            if elevator.get_direction() == Direction.DOWN
            else heapq.heappop(opposite_queue)
        )
