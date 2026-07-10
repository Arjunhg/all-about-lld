# frozen_string_literal: true

require_relative "../food_item"

class BonusFood < FoodItem
  def initialize(row, col)
    super(row, col)
    @points = 5
  end
end
