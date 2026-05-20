from GameStateHandler.game_state import GameState


class OWonState(GameState):
    def next(self, context, player, has_won: bool):
        return

    def is_game_over(self) -> bool:
        return True

    def get_result_message(self) -> str:
        return "Player O has won the game!"
