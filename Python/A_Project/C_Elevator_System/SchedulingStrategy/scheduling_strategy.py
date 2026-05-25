from abc import ABC, abstractmethod


class SchedulingStrategy(ABC):
    @abstractmethod
    def get_next_stop(self, elevator) -> int:
        pass
