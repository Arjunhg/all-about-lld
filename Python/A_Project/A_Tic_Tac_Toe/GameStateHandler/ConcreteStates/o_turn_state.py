from CentralEnum.symbol import Symbol
from GameStateHandler.game_state import GameState


class OTurnState(GameState):
    def next(self, context, player, has_won: bool):
        if has_won:
            from GameStateHandler.ConcreteStates.o_won_state import OWonState
            from GameStateHandler.ConcreteStates.x_won_state import XWonState

            context.set_state(OWonState() if player.get_symbol() == Symbol.O else XWonState())
        else:
            from GameStateHandler.ConcreteStates.x_turn_state import XTurnState

            context.set_state(XTurnState())

    def is_game_over(self) -> bool:
        return False

    def get_result_message(self) -> str:
        return "Game is still in progress."
