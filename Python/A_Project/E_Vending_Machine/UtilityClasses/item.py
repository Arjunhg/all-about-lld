class Item:
    def __init__(self):
        self._item_type = None
        self._price = 0

    def get_type(self):
        return self._item_type

    def set_type(self, item_type):
        self._item_type = item_type

    def get_price(self) -> int:
        return self._price

    def set_price(self, price: int):
        self._price = price
