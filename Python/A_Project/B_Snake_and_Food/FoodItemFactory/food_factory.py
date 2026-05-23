from typing import Sequence

from FoodItemFactory.ConcreteFoodItem.bonus_food import BonusFood
from FoodItemFactory.ConcreteFoodItem.normal_food import NormalFood


class FoodFactory:
    @staticmethod
    def create_food(pos: Sequence[int], food_type: str):
        try:
            row, col = pos[0], pos[1]
        except (TypeError, IndexError):
            raise ValueError("Position must contain at least 2 elements") from None
        if food_type == "bonus":
            return BonusFood(row, col)
        return NormalFood(row, col)
