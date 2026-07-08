require_relative 'Controller/GameController/tic_tac_toe_game'
require_relative 'PlayerStrategies/ConcreteStrategy/human_player_strategy'

player_x_strategy = PlayerStrategies::ConcreteStrategy::HumanPlayerStrategy.new("Player X")
player_o_strategy = PlayerStrategies::ConcreteStrategy::HumanPlayerStrategy.new("Player O")

game = Controller::GameController::TicTacToeGame.new(player_x_strategy, player_o_strategy, 3, 3)
game.play
