from collections import deque

from CommonEnums.direction import Direction
from CommonEnums.elevator_state import ElevatorState


class Elevator:
    def __init__(self, elevator_id: int):
        self._id = elevator_id
        self._current_floor = 0
        self._direction = Direction.IDLE
        self._state = ElevatorState.IDLE
        self._observers = []
        self._requests = deque()

    def add_observer(self, observer):
        self._observers.append(observer)

    def remove_observer(self, observer):
        if observer in self._observers:
            self._observers.remove(observer)

    def notify_state_change(self):
        for observer in self._observers:
            observer.on_elevator_state_change(self, self._state)

    def notify_floor_change(self, floor: int):
        for observer in self._observers:
            observer.on_elevator_floor_change(self, floor)

    def get_direction(self) -> Direction:
        return self._direction

    def set_direction(self, new_direction: Direction):
        self._direction = new_direction

    def set_state(self, new_state: ElevatorState):
        self._state = new_state
        self.notify_state_change()

    def add_request(self, request):
        if request not in self._requests:
            self._requests.append(request)

        requested_floor = request.get_floor()
        if self._state == ElevatorState.IDLE and request is not None:
            if requested_floor == self._current_floor:
                self._complete_arrival()
                return
            if requested_floor > self._current_floor:
                self.set_direction(Direction.UP)
            elif requested_floor < self._current_floor:
                self.set_direction(Direction.DOWN)
            self.set_state(ElevatorState.MOVING)

    def move_to_next_stop(self, next_stop: int):
        if self._state != ElevatorState.MOVING:
            return

        if next_stop == self._current_floor:
            self._complete_arrival()
            return

        self.set_direction(
            Direction.UP if next_stop > self._current_floor else Direction.DOWN
        )

        while self._current_floor != next_stop:
            self._current_floor += 1 if self._direction == Direction.UP else -1
            self.notify_floor_change(self._current_floor)
        self._complete_arrival()

    def _complete_arrival(self):
        self.set_state(ElevatorState.STOPPED)
        self._requests = deque(
            [request for request in self._requests if request.get_floor() != self._current_floor]
        )
        if not self._requests:
            self.set_direction(Direction.IDLE)
            self.set_state(ElevatorState.IDLE)
        else:
            self.set_state(ElevatorState.MOVING)

    def complete_request(self):
        self._complete_arrival()

    def get_elevator_id(self) -> int:
        return self._id

    def get_current_floor(self) -> int:
        return self._current_floor

    def get_state(self) -> ElevatorState:
        return self._state

    def get_request_queue(self):
        return list(self._requests)

    def get_destination_floors(self):
        return list(self._requests)
