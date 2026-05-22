class ScoreService:
    def __init__(self):
        self.score = 0

    def add(self, item):
        if item is not None:
            self.score += item.get_points()

    def get_score(self) -> int:
        return self.score

    def reset(self):
        self.score = 0
