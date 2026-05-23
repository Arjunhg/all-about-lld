from Utility.pair import Pair


class TextBoardRenderer:
    def render(self, board, snake_body, current_food):
        height = board.get_height()
        width = board.get_width()

        body_set = set(snake_body)
        head = snake_body[0] if len(snake_body) > 0 else None

        for r in range(height):
            row_cells = []
            for c in range(width):
                cell = "."
                if head is not None and head.row == r and head.col == c:
                    cell = "H"
                elif Pair(r, c) in body_set:
                    cell = "S"
                elif current_food is not None and current_food[0] == r and current_food[1] == c:
                    cell = "F"
                row_cells.append(cell)
            print(" ".join(row_cells))
        print()
