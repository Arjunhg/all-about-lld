class ItemShelf:
    def __init__(self, code: int):
        self._code = code
        self._items = []

    def is_sold_out(self) -> bool:
        return len(self._items) == 0

    def get_items(self):
        return list(self._items)

    def get_code(self) -> int:
        return self._code

    def add_item(self, item):
        self._items.append(item)

    def remove_item(self, item):
        if item in self._items:
            self._items.remove(item)
