from CentralEnum.symbol import Symbol
from GameStateHandler.game_state import GameState


class XTurnState(GameState):
    def next(self, context, player, has_won: bool):
        if has_won:
            from GameStateHandler.ConcreteStates.x_won_state import XWonState
            context.set_state(XWonState())
        else:
            from GameStateHandler.ConcreteStates.o_turn_state import OTurnState

            context.set_state(OTurnState())

    def is_game_over(self) -> bool:
        return False

    def get_result_message(self) -> str:
        return "Game is still in progress."
