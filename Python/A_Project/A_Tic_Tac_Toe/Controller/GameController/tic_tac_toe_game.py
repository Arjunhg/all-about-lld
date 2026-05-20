from CentralEnum.symbol import Symbol
from Controller.board_games import BoardGames
from GameStateHandler.Context.game_context import GameContext
from PlayerStrategies.player_strategy import PlayerStrategy
from Utility.board import Board
from Utility.player import Player


class TicTacToeGame(BoardGames):
    def __init__(self, x_strategy: PlayerStrategy, o_strategy: PlayerStrategy, rows: int, cols: int):
        self.board = Board(rows, cols)
        self.player_x = Player(Symbol.X, x_strategy)
        self.player_o = Player(Symbol.O, o_strategy)
        self.current_player = self.player_x
        self.game_context = GameContext()

    def play(self):
        while True:
            self.board.display_board()

            move = self.current_player.get_player_strategy().make_move(self.board)
            self.board.make_move(move, self.current_player.get_symbol())

            self.board.check_game_state(self.game_context, self.current_player)
            if self.game_context.is_game_over():
                break
            self._switch_player()

        self._announce_result()

    def _switch_player(self):
        self.current_player = self.player_o if self.current_player == self.player_x else self.player_x

    def _announce_result(self):
        self.board.display_board()
        if self.game_context is None or self.game_context.get_current_state() is None:
            print("Game result unavailable.")
            return
        print(self.game_context.get_current_state().get_result_message())
