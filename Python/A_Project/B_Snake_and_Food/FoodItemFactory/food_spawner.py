from FoodItemFactory.food_factory import FoodFactory


class FoodSpawner:
    def __init__(self, positions, bonus_every_k: int):
        self.positions = positions
        self.bonus_every_k = max(2, bonus_every_k)
        self.index = 0

    def has_more(self) -> bool:
        return self.index < len(self.positions)

    def current_item(self):
        if not self.has_more():
            return None
        pos = self.positions[self.index]
        food_type = "bonus" if ((self.index + 1) % self.bonus_every_k) == 0 else "normal"
        return FoodFactory.create_food(pos, food_type)

    def consume(self):
        if self.has_more():
            self.index += 1

    def get_index(self) -> int:
        return self.index
