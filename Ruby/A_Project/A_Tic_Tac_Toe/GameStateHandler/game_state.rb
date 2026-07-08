# State Pattern: Game State Management
#
# What is the State Pattern?
# • A behavioral design pattern that allows objects to change behavior based on internal state
# • Enables clean state transitions without complex conditional logic
#
# How does it work in our game?
# • GameState module defines different states a Tic-Tac-Toe game can be in:
#   - IN_PROGRESS: Game is still being played (XTurnState / OTurnState)
#   - X_WON: Player X has won the game
#   - O_WON: Player O has won the game
#   - DRAW: Game ended with no winner
#
# Key Benefits:
# • Each state knows when the game is over (game_over? method)
# • State transitions are handled cleanly via next() method
# • Easy to add new game states without modifying existing code
# • Separates state-specific logic from the main game context

module GameStateHandler
  module GameState
    # Transition to the next appropriate state.
    # context  - GameContext object
    # player   - current Player
    # has_won  - boolean indicating if the current player has won
    def next(context, player, has_won)
      raise NotImplementedError, "#{self.class}#next is not implemented"
    end

    # Returns true when the game has ended.
    def game_over?
      raise NotImplementedError, "#{self.class}#game_over? is not implemented"
    end

    # Returns the human-readable result message.
    def result_message
      raise NotImplementedError, "#{self.class}#result_message is not implemented"
    end
  end
end
