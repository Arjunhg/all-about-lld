require_relative '../game_state'

module GameStateHandler
  module ConcreteStates
    class DrawState
      include GameState

      def next(context, player, has_won)
        # Game is over, no further state transitions
      end

      def game_over?
        true
      end

      def result_message
        "The game ended in a draw!"
      end
    end
  end
end
