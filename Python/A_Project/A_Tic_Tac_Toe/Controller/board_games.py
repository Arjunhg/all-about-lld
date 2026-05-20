from abc import ABC, abstractmethod


class BoardGames(ABC):
    @abstractmethod
    def play(self):
        pass
