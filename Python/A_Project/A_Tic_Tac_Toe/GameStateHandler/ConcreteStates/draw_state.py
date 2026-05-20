from GameStateHandler.game_state import GameState


class DrawState(GameState):
    def next(self, context, player, has_won: bool):
        return

    def is_game_over(self) -> bool:
        return True

    def get_result_message(self) -> str:
        return "The game ended in a draw!"
