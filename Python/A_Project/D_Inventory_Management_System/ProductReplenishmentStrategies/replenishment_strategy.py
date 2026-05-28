from abc import ABC, abstractmethod


class ReplenishmentStrategy(ABC):
    @abstractmethod
    def replenish(self, product):
        pass
