from abc import ABC, abstractmethod


class MovementStrategy(ABC):
    @abstractmethod
    def get_next_position(self, current_head, direction: str):
        pass
