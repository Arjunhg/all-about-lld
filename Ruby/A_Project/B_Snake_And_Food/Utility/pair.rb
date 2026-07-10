# frozen_string_literal: true

class Pair
  attr_reader :row, :col

  def initialize(row, col)
    @row = row
    @col = col
    freeze
  end

  def ==(other)
    other.is_a?(Pair) && other.row == row && other.col == col
  end

  alias eql? ==

  def hash
    [row, col].hash
  end

  def to_s
    "(#{row},#{col})"
  end
end
