require_relative '../game_state'
require_relative '../ConcreteStates/x_turn_state'
require_relative '../ConcreteStates/o_turn_state'
require_relative '../ConcreteStates/x_won_state'
require_relative '../ConcreteStates/o_won_state'
require_relative '../ConcreteStates/draw_state'

module GameStateHandler
  module Context
    class GameContext
      attr_accessor :state

      def initialize
        # Start with X's turn
        @state = ConcreteStates::XTurnState.new
      end

      def next(player, has_won)
        @state.next(self, player, has_won)
      end

      def game_over?
        @state.game_over?
      end

      def current_state
        @state
      end
    end
  end
end
