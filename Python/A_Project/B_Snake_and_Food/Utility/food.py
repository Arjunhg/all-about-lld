class Food:
    def __init__(self, food_positions):
        self.food_positions = food_positions
        self.current_food_index = 0

    def has_more(self) -> bool:
        return self.current_food_index < len(self.food_positions)

    def current(self):
        if not self.has_more():
            return None
        return self.food_positions[self.current_food_index]

    def consume(self):
        if self.has_more():
            self.current_food_index += 1
