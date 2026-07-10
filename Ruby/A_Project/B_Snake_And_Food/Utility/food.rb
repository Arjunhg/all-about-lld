# frozen_string_literal: true

class Food
  def initialize(food_positions)
    @food_positions = food_positions
    @current_food_index = 0
  end

  def has_more
    @current_food_index < @food_positions.length
  end

  def current
    return nil unless has_more

    @food_positions[@current_food_index]
  end

  def consume
    @current_food_index += 1 if has_more
  end
end
