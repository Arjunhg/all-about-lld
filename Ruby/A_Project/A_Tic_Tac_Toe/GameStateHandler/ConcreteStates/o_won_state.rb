require_relative '../game_state'

module GameStateHandler
  module ConcreteStates
    class OWonState
      include GameState

      def next(context, player, has_won)
        # Game is already over, no further state transitions
      end

      def game_over?
        true
      end

      def result_message
        "Player O has won the game!"
      end
    end
  end
end
