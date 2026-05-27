class Product:
    def __init__(self):
        self._id = None
        self._name = None
        self._price = 0.0
        self._quantity = 0
        self._threshold = 0
        self._category = None

    def get_id(self):
        return self._id

    def set_id(self, product_id: str):
        self._id = product_id

    def get_name(self):
        return self._name

    def set_name(self, name: str):
        self._name = name

    def get_price(self):
        return self._price

    def set_price(self, price: float):
        self._price = price

    def get_quantity(self):
        return self._quantity

    def set_quantity(self, quantity: int):
        self._quantity = quantity

    def get_threshold(self):
        return self._threshold

    def set_threshold(self, threshold: int):
        self._threshold = threshold

    def get_category(self):
        return self._category

    def set_category(self, category):
        self._category = category
