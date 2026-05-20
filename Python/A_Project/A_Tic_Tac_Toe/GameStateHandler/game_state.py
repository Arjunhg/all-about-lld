from abc import ABC, abstractmethod


class GameState(ABC):
    @abstractmethod
    def next(self, context, player, has_won: bool):
        pass

    @abstractmethod
    def is_game_over(self) -> bool:
        pass

    @abstractmethod
    def get_result_message(self) -> str:
        pass
