from CommonExceptions.invalid_shelf_code_exception import InvalidShelfCodeException
from CommonExceptions.item_sold_out_exception import ItemSoldOutException
from UtilityClasses.item_shelf import ItemShelf


class Inventory:
    def __init__(self, number_of_shelves: int):
        self._inventory = {}
        self.initialize_empty_inventory(number_of_shelves)

    def get_inventory(self):
        return dict(self._inventory)

    def initialize_empty_inventory(self, number_of_shelves: int):
        start_code = 101
        for _ in range(number_of_shelves):
            self._inventory[start_code] = ItemShelf(start_code)
            start_code += 1

    def add_item(self, item, code_number: int):
        shelf = self._inventory.get(code_number)
        if shelf is None:
            raise InvalidShelfCodeException(f"Invalid shelf code: {code_number}")
        shelf.add_item(item)

    def get_item(self, code_number: int):
        shelf = self._inventory.get(code_number)
        if shelf is None:
            raise InvalidShelfCodeException(f"Invalid shelf code: {code_number}")
        if shelf.is_sold_out():
            raise ItemSoldOutException(f"Shelf is sold out: {code_number}")
        return shelf.get_items()[0]

    def remove_item(self, code_number: int):
        shelf = self._inventory.get(code_number)
        if shelf is None:
            raise InvalidShelfCodeException(f"Invalid shelf code: {code_number}")
        if shelf.is_sold_out():
            raise ItemSoldOutException(
                f"Cannot remove item. Shelf is sold out: {code_number}"
            )
        shelf.remove_item(shelf.get_items()[0])
