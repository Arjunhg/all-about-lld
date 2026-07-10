# frozen_string_literal: true

require "set"

require_relative "../FoodItemFactory/food_spawner"
require_relative "../MovementStrategyModule/ConcreteMovementStrategies/human_movement_strategy"
require_relative "../Utility/food"
require_relative "../Utility/game_board"
require_relative "../Utility/pair"
require_relative "../Utility/score_service"
require_relative "../Utility/snake"
require_relative "../Utility/text_board_renderer"

class SnakeGame
  def initialize(width, height, food_positions)
    @game_board = GameBoard.get_instance(width, height)
    @food = Food.new(food_positions)
    @food_spawner = FoodSpawner.new(food_positions, 3)

    start_row = height / 2
    start_col = width / 2
    @snake = Snake.new(Pair.new(start_row, start_col))
    @snake_map = Set.new([@snake.head])
    @score_service = ScoreService.new
    @renderer = TextBoardRenderer.new
    @movement_strategy = HumanMovementStrategy.new

    @renderer.render(@game_board, @snake.body, @food.current)
  end

  def set_movement_strategy(strategy)
    @movement_strategy = strategy
  end

  def move(direction)
    curr_head = @snake.head
    new_head = @movement_strategy.get_next_position(curr_head, direction)

    new_head_row = new_head.row
    new_head_col = new_head.col
    crosses_boundary = new_head_row < 0 || new_head_row >= @game_board.get_height || new_head_col < 0 || new_head_col >= @game_board.get_width

    current_item = @food_spawner.current_item
    ate_food = false
    if !current_item.nil?
      ate_food = current_item.row == new_head_row && current_item.col == new_head_col
    end

    curr_tail = @snake.tail
    self_collision = @snake_map.include?(new_head) && !(new_head == curr_tail && !ate_food)

    return -1 if crosses_boundary || self_collision

    if ate_food
      @score_service.add(current_item)
      @food_spawner.consume
      @food.consume
      @snake.grow_to(new_head)
    else
      @snake.move_to(new_head)
      @snake_map.delete(curr_tail)
    end

    @snake_map.add(new_head)
    @renderer.render(@game_board, @snake.body, @food.current)
    @score_service.get_score
  end

  def get_snake
    @snake.body
  end

  def get_score
    @score_service.get_score
  end
end
