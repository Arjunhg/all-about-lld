# frozen_string_literal: true

require_relative "ConcreteFoodItem/bonus_food"
require_relative "ConcreteFoodItem/normal_food"

class FoodFactory
  def self.create_food(pos, food_type)
    row = pos[0]
    col = pos[1]

    raise ArgumentError, "Position must contain at least 2 elements" if row.nil? || col.nil?

    if food_type == "bonus"
      BonusFood.new(row, col)
    else
      NormalFood.new(row, col)
    end
  end
end
