from FoodItemFactory.ConcreteFoodItem.bonus_food import BonusFood
from FoodItemFactory.ConcreteFoodItem.normal_food import NormalFood


class FoodFactory:
    @staticmethod
    def create_food(pos, food_type: str):
        if food_type == "bonus":
            return BonusFood(pos[0], pos[1])
        return NormalFood(pos[0], pos[1])
