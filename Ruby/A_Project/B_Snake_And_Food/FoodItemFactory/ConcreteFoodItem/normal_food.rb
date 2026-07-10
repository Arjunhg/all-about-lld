# frozen_string_literal: true

require_relative "../food_item"

class NormalFood < FoodItem
  def initialize(row, col)
    super(row, col)
    @points = 1
  end
end
