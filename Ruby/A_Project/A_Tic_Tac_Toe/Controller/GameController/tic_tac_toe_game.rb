# TicTacToeGame Class - Game Flow Controller
#
# This class manages the complete game experience by handling:
#
# • Game Board Management
#   - Initializes and maintains the game board state
#   - Tracks cell occupancy and game progress
#
# • Player Management
#   - Sets up both players (X and O) with their respective strategies
#   - Manages player-specific game logic and moves
#
# • Turn Management
#   - Controls whose turn it is to play
#   - Defaults to playerX at game start
#   - Alternates turns between players throughout the game
#
# • Game Flow Control
#   - Orchestrates the overall game experience
#   - Coordinates between board, players, and turn management
#
# Usage: Acts as the main controller for TicTacToe game logic

require_relative '../board_games'
require_relative '../../CentralEnum/symbol'
require_relative '../../GameStateHandler/Context/game_context'
require_relative '../../Utility/board'
require_relative '../../Utility/player'

module Controller
  module GameController
    class TicTacToeGame
      include BoardGames

      def initialize(x_strategy, o_strategy, rows, cols)
        @board          = Utility::Board.new(rows, cols)
        @player_x       = Utility::Player.new(CentralEnum::Symbol::X, x_strategy)
        @player_o       = Utility::Player.new(CentralEnum::Symbol::O, o_strategy)
        @current_player = @player_x   # X starts first by default
        @game_context   = GameStateHandler::Context::GameContext.new
      end

      def play
        # Continue until game is over
        loop do
          # Show current state of game
          @board.display_board

          # Current player makes a move
          move = @current_player.player_strategy.make_move(@board)
          @board.make_move(move, @current_player.symbol)

          # Check game state after the move
          @board.check_game_state(@game_context, @current_player)
          switch_player unless @game_context.game_over?

          break if @game_context.game_over?
        end

        announce_result
      end

      private

      # Switch to the other player alternatively
      def switch_player
        @current_player = (@current_player == @player_x) ? @player_o : @player_x
      end

      # Announce the final result of the game based on game state
      def announce_result
        @board.display_board
        if @game_context.current_state.nil?
          puts "Game result unavailable."
          return
        end
        puts @game_context.current_state.result_message  # module-driven polymorphism
      end
    end
  end
end
