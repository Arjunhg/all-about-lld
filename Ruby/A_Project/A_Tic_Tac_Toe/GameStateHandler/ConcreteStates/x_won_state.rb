require_relative '../game_state'

module GameStateHandler
  module ConcreteStates
    class XWonState
      include GameState

      def next(context, player, has_won)
        # Game is over, no further state transitions
      end

      def game_over?
        true
      end

      def result_message
        "Player X has won the game!"
      end
    end
  end
end
