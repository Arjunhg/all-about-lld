from MovementStrategyModule.movement_strategy import MovementStrategy
from Utility.pair import Pair


class HumanMovementStrategy(MovementStrategy):
    def get_next_position(self, current_head, direction: str):
        row = current_head.row
        col = current_head.col
        if direction == "U":
            return Pair(row - 1, col)
        if direction == "D":
            return Pair(row + 1, col)
        if direction == "L":
            return Pair(row, col - 1)
        if direction == "R":
            return Pair(row, col + 1)
        return current_head
