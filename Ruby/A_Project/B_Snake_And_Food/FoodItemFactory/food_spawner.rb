# frozen_string_literal: true

require_relative "food_factory"

class FoodSpawner
  def initialize(positions, bonus_every_k)
    unless positions.respond_to?(:empty?) && !positions.empty?
      raise ArgumentError, "positions must be a non-empty list or tuple"
    end

    @positions = positions
    @bonus_every_k = [bonus_every_k.to_i, 2].max
    @index = 0
  end

  def has_more
    @index < @positions.length
  end

  def current_item
    return nil unless has_more

    pos = @positions[@index]
    food_type = ((@index + 1) % @bonus_every_k).zero? ? "bonus" : "normal"
    FoodFactory.create_food(pos, food_type)
  end

  def consume
    @index += 1 if has_more
  end

  def get_index
    @index
  end
end
