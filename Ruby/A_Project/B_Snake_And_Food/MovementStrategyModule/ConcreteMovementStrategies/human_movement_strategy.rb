# frozen_string_literal: true

require_relative "../movement_strategy"
require_relative "../../Utility/pair"

class HumanMovementStrategy < MovementStrategy
  def get_next_position(current_head, direction)
    row = current_head.row
    col = current_head.col

    case direction
    when "U"
      Pair.new(row - 1, col)
    when "D"
      Pair.new(row + 1, col)
    when "L"
      Pair.new(row, col - 1)
    when "R"
      Pair.new(row, col + 1)
    else
      current_head
    end
  end
end
