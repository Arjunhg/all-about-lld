from MovementStrategyModule.ConcreteMovementStrategies.human_movement_strategy import (
    HumanMovementStrategy,
)
from Utility.game_board import GameBoard
from Utility.pair import Pair
from Utility.food import Food
from Utility.snake import Snake
from Utility.score_service import ScoreService
from Utility.text_board_renderer import TextBoardRenderer
from FoodItemFactory.food_spawner import FoodSpawner


class SnakeGame:
    def __init__(self, width: int, height: int, food_positions):
        self.game_board = GameBoard.get_instance(width, height)
        self.food = Food(food_positions)
        self.food_spawner = FoodSpawner(food_positions, 3)

        start_row = height // 2
        start_col = width // 2
        self.snake = Snake(Pair(start_row, start_col))
        self.snake_map = {self.snake.head()}

        self.score_service = ScoreService()
        self.renderer = TextBoardRenderer()
        self.movement_strategy = HumanMovementStrategy()

        self.renderer.render(self.game_board, self.snake.body(), self.food.current())

    def set_movement_strategy(self, strategy):
        self.movement_strategy = strategy

    def move(self, direction: str) -> int:
        curr_head = self.snake.head()
        new_head = self.movement_strategy.get_next_position(curr_head, direction)
        new_head_row = new_head.row
        new_head_col = new_head.col

        crosses_boundary = (
            new_head_row < 0
            or new_head_row >= self.game_board.get_height()
            or new_head_col < 0
            or new_head_col >= self.game_board.get_width()
        )

        curr_tail = self.snake.tail()
        self_collision = (new_head in self.snake_map) and not (new_head == curr_tail)

        if crosses_boundary or self_collision:
            return -1

        current_item = self.food_spawner.current_item()
        ate_food = False
        if current_item is not None:
            ate_food = (current_item.row == new_head_row) and (current_item.col == new_head_col)

        if ate_food:
            self.score_service.add(current_item)
            self.food_spawner.consume()
            self.food.consume()
            self.snake.grow_to(new_head)
        else:
            self.snake.move_to(new_head)
            self.snake_map.discard(curr_tail)

        self.snake_map.add(new_head)
        self.renderer.render(self.game_board, self.snake.body(), self.food.current())

        return self.score_service.get_score()

    def get_snake(self):
        return self.snake.body()

    def get_score(self) -> int:
        return self.score_service.get_score()
