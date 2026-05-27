from CommonEnums.product_enum import ProductEnum
from ProductFactory.product import Product


class ClothingProduct(Product):
    def __init__(self, product_id, name, price, quantity, threshold):
        super().__init__()
        self._size = None
        self._color = None
        self.set_id(product_id)
        self.set_name(name)
        self.set_price(price)
        self.set_quantity(quantity)
        self.set_threshold(threshold)
        self.set_category(ProductEnum.CLOTHING)

    def get_size(self):
        return self._size

    def set_size(self, size: str):
        self._size = size

    def get_color(self):
        return self._color

    def set_color(self, color: str):
        self._color = color
