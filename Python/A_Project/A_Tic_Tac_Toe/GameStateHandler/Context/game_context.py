from GameStateHandler.ConcreteStates.x_turn_state import XTurnState


class GameContext:
    def __init__(self):
        self._current_state = XTurnState()

    def set_state(self, state):
        self._current_state = state

    def next(self, player, has_won: bool):
        self._current_state.next(self, player, has_won)

    def is_game_over(self) -> bool:
        return self._current_state.is_game_over()

    def get_current_state(self):
        return self._current_state
