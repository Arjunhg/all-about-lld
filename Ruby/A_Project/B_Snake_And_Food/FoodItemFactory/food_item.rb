# frozen_string_literal: true

class FoodItem
  attr_reader :row, :col, :points

  def initialize(row, col)
    @row = row
    @col = col
    @points = 0
  end

  def get_row
    @row
  end

  def get_col
    @col
  end

  def get_points
    @points
  end
end
