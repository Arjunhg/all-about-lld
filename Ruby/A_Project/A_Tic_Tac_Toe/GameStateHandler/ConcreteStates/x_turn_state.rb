require_relative '../game_state'

module GameStateHandler
  module ConcreteStates
    class XTurnState
      include GameState

      def next(context, player, has_won)
        # After a player has won we transition to the appropriate winning state.
        # context is needed here to allow clean state transition.
        if has_won
          require_relative './x_won_state'
          require_relative './o_won_state'
          context.state = (player.symbol == CentralEnum::Symbol::X ? XWonState.new : OWonState.new)
        else
          require_relative './o_turn_state'
          context.state = OTurnState.new
        end
      end

      def game_over?
        false
      end

      def result_message
        "Game is still in progress."
      end
    end
  end
end
