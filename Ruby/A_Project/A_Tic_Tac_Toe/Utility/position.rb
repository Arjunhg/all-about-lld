# POSITION CLASS OVERVIEW
#
# • Purpose: Represents row and column coordinates on the game board
# • Class Name: Position
#
# Key Features:
# • Encapsulates board position data
# • Used to represent move locations
# • Supports equality checks
# • Provides readable formatting
#
# Usage: Essential for tracking player moves and board state management

module Utility
  class Position
    attr_accessor :row, :col

    def initialize(row, col)
      @row = row
      @col = col
    end

    def to_s
      "(#{@row}, #{@col})"
    end

    def ==(other)
      return false unless other.is_a?(Position)
      @row == other.row && @col == other.col
    end

    def eql?(other)
      self == other
    end

    def hash
      (31 * @row) + @col
    end
  end
end
