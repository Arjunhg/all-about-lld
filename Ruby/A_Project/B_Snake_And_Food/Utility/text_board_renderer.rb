# frozen_string_literal: true

require "set"

require_relative "pair"

class TextBoardRenderer
  def render(board, snake_body, current_food)
    height = board.get_height
    width = board.get_width
    body_set = snake_body.to_set
    head = snake_body[0]

    height.times do |row|
      row_cells = []
      width.times do |col|
        cell = "."
        if !head.nil? && head.row == row && head.col == col
          cell = "H"
        elsif body_set.include?(Pair.new(row, col))
          cell = "S"
        elsif !current_food.nil? && current_food[0] == row && current_food[1] == col
          cell = "F"
        end
        row_cells << cell
      end
      puts row_cells.join(" ")
    end
    puts
  end
end
