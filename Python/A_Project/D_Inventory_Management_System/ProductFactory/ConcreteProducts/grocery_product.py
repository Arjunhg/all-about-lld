from datetime import date

from CommonEnums.product_enum import ProductEnum
from ProductFactory.product import Product


class GroceryProduct(Product):
    def __init__(self, product_id, name, price, quantity, threshold):
        super().__init__()
        self._expiry_date = None
        self._refrigerated = False
        self.set_id(product_id)
        self.set_name(name)
        self.set_price(price)
        self.set_quantity(quantity)
        self.set_threshold(threshold)
        self.set_category(ProductEnum.GROCERY)

    def get_expiry_date(self):
        return self._expiry_date

    def set_expiry_date(self, expiry_date: date):
        self._expiry_date = expiry_date

    def is_refrigerated(self):
        return self._refrigerated

    def set_refrigerated(self, refrigerated: bool):
        self._refrigerated = refrigerated
