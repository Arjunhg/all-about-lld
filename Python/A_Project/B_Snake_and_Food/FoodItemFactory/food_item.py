class FoodItem:
    def __init__(self, row: int, col: int):
        self.row = row
        self.col = col
        self.points = 0

    def get_row(self) -> int:
        return self.row

    def get_col(self) -> int:
        return self.col

    def get_points(self) -> int:
        return self.points
