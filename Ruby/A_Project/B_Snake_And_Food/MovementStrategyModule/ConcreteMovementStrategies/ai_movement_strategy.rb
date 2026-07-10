# frozen_string_literal: true

require_relative "../movement_strategy"

class AIMovementStrategy < MovementStrategy
  def get_next_position(current_head, _direction)
    current_head
  end
end
