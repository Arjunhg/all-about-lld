from abc import ABC, abstractmethod


class PlayerStrategy(ABC):
    @abstractmethod
    def make_move(self, board):
        pass
