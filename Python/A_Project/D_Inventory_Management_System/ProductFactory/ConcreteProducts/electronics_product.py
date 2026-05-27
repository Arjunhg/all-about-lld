from CommonEnums.product_enum import ProductEnum
from ProductFactory.product import Product


class ElectronicsProduct(Product):
    def __init__(self, product_id, name, price, quantity, threshold):
        super().__init__()
        self._brand = None
        self._warranty_period = 0
        self.set_id(product_id)
        self.set_name(name)
        self.set_price(price)
        self.set_quantity(quantity)
        self.set_threshold(threshold)
        self.set_category(ProductEnum.ELECTRONICS)

    def get_brand(self):
        return self._brand

    def set_brand(self, brand: str):
        self._brand = brand

    def get_warranty_period(self):
        return self._warranty_period

    def set_warranty_period(self, warranty_period: int):
        self._warranty_period = warranty_period
