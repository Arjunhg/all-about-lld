require_relative '../game_state'

module GameStateHandler
  module ConcreteStates
    class OTurnState
      include GameState

      def next(context, player, has_won)
        if has_won
          require_relative './x_won_state'
          require_relative './o_won_state'
          context.state = (player.symbol == CentralEnum::Symbol::O ? OWonState.new : XWonState.new)
        else
          require_relative './x_turn_state'
          context.state = XTurnState.new
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
