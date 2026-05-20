from dataclasses import dataclass


@dataclass(frozen=True)
class Position:
    row: int
    col: int

    def __str__(self) -> str:
        return f"({self.row}, {self.col})"
