from PlayerStrategies.player_strategy import PlayerStrategy
from Utility.position import Position


class HumanPlayerStrategy(PlayerStrategy):
    def __init__(self, player_name: str):
        self.player_name = player_name

    def make_move(self, board):
        while True:
            prompt = (
                f"{self.player_name}, enter your move (row [0-{board.rows - 1}] "
                f"and column [0-{board.cols - 1}]): "
            )
            raw = input(prompt)
            parts = raw.strip().split()
            if len(parts) < 2:
                print("Invalid input. Please enter numbers for row and column.")
                continue
            try:
                row = int(parts[0])
                col = int(parts[1])
            except ValueError:
                print("Invalid input. Please enter numbers for row and column.")
                continue

            move = Position(row, col)
            if board.is_valid_move(move):
                return move

            print("This move is not valid. Try again.")
