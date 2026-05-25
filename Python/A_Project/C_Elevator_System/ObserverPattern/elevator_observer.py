from abc import ABC, abstractmethod


class ElevatorObserver(ABC):
    @abstractmethod
    def on_elevator_state_change(self, elevator, state):
        pass

    @abstractmethod
    def on_elevator_floor_change(self, elevator, floor: int):
        pass
