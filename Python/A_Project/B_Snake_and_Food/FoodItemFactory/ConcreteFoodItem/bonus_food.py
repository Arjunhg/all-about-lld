from FoodItemFactory.food_item import FoodItem


class BonusFood(FoodItem):
    def __init__(self, row: int, col: int):
        super().__init__(row, col)
        self.points = 5
