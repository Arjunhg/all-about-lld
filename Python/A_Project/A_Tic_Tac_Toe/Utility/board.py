from CentralEnum.symbol import Symbol
from GameStateHandler.ConcreteStates.draw_state import DrawState


class Board:
    def __init__(self, rows: int, cols: int):
        if rows <= 0 or cols <= 0:
            raise ValueError("rows and cols must be greater than 0")
        self.rows = rows
        self.cols = cols
        self.grid = [[Symbol.EMPTY for _ in range(cols)] for _ in range(rows)]

    def is_valid_move(self, pos) -> bool:
        if pos is None:
            return False
        return (
            0 <= pos.row < self.rows
            and 0 <= pos.col < self.cols
            and self.grid[pos.row][pos.col] == Symbol.EMPTY
        )

    def make_move(self, pos, symbol):
        if not self.is_valid_move(pos):
            raise ValueError("Invalid move: position out of bound or already occupied")
        self.grid[pos.row][pos.col] = symbol

    def check_game_state(self, context, curr_player):
        for i in range(self.rows):
            if self.grid[i][0] != Symbol.EMPTY and self._is_winning_line(self.grid[i]):
                context.next(curr_player, True)
                return

        for i in range(self.cols):
            column = [self.grid[j][i] for j in range(self.rows)]
            if column[0] != Symbol.EMPTY and self._is_winning_line(column):
                context.next(curr_player, True)
                return

        diag_len = min(self.rows, self.cols)
        if diag_len > 0:
            diag1 = [self.grid[i][i] for i in range(diag_len)]
            diag2 = [self.grid[i][self.cols - 1 - i] for i in range(diag_len)]
            if diag1[0] != Symbol.EMPTY and self._is_winning_line(diag1):
                context.next(curr_player, True)
                return
            if diag2[0] != Symbol.EMPTY and self._is_winning_line(diag2):
                context.next(curr_player, True)
                return

        if self._is_draw():
            context.set_state(DrawState())

    def _is_winning_line(self, line) -> bool:
        if not line:
            return False
        first = line[0]
        return all(symbol == first for symbol in line)

    def _is_draw(self) -> bool:
        for row in self.grid:
            for cell in row:
                if cell == Symbol.EMPTY:
                    return False
        return True

    def display_board(self):
        for i in range(self.rows):
            for j in range(self.cols):
                symbol = self.grid[i][j]
                if symbol == Symbol.X:
                    cell = " X "
                elif symbol == Symbol.O:
                    cell = " O "
                else:
                    cell = " . "

                print(cell, end="")
                if j < self.cols - 1:
                    print("|", end="")
            print()
            if i < self.rows - 1:
                sep = []
                for c in range(self.cols):
                    sep.append("---")
                    if c < self.cols - 1:
                        sep.append("+")
                print("".join(sep))
        print()
