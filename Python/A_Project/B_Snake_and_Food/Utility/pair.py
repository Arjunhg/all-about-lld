from dataclasses import dataclass


@dataclass(frozen=True)
class Pair:
    row: int
    col: int

    def __str__(self) -> str:
        return f"({self.row},{self.col})"
