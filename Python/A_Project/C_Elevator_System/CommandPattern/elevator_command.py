from abc import ABC, abstractmethod


class ElevatorCommand(ABC):
    @abstractmethod
    def execute(self):
        pass
