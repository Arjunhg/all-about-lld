from CommonEnums.product_enum import ProductEnum
from ProductFactory.ConcreteProducts.clothing_product import ClothingProduct
from ProductFactory.ConcreteProducts.electronics_product import ElectronicsProduct
from ProductFactory.ConcreteProducts.grocery_product import GroceryProduct


class ProductFactory:
    def create_product(self, category, product_id, name, price, quantity, threshold):
        if category == ProductEnum.ELECTRONICS:
            return ElectronicsProduct(product_id, name, price, quantity, threshold)
        if category == ProductEnum.CLOTHING:
            return ClothingProduct(product_id, name, price, quantity, threshold)
        if category == ProductEnum.GROCERY:
            return GroceryProduct(product_id, name, price, quantity, threshold)
        raise ValueError(f"Unsupported product category: {category}")
