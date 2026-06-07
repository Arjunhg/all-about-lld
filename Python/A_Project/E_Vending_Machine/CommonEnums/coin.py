from enum import Enum


class Coin(Enum):
    ONE_RUPEE = 1
    TWO_RUPEE = 2
    FIVE_RUPEE = 5
    TEN_RUPEE = 10

    def get_value(self) -> int:
        return self.value
