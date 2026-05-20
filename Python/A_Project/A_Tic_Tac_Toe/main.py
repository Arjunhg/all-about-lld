from Controller.GameController.tic_tac_toe_game import TicTacToeGame
from PlayerStrategies.ConcreteStrategy.human_player_strategy import HumanPlayerStrategy


def main():
    player_x_strategy = HumanPlayerStrategy("Player X")
    player_o_strategy = HumanPlayerStrategy("Player O")
    game = TicTacToeGame(player_x_strategy, player_o_strategy, 3, 3)
    game.play()


if __name__ == "__main__":
    main()
