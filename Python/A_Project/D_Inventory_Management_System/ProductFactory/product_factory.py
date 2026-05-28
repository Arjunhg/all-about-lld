from CommonEnums.product_enum import ProductEnum
from ProductFactory.ConcreteProducts.clothing_product import ClothingProduct
from ProductFactory.ConcreteProducts.electronics_product import ElectronicsProduct
from ProductFactory.ConcreteProducts.grocery_product import GroceryProduct
from ProductFactory.product import Product


class ProductFactory:
    def create_product(self, category, product_id, name, price, quantity, threshold):
        if category == ProductEnum.ELECTRONICS:
            return ElectronicsProduct(product_id, name, price, quantity, threshold)
        if category == ProductEnum.CLOTHING:
            return ClothingProduct(product_id, name, price, quantity, threshold)
        if category == ProductEnum.GROCERY:
            return GroceryProduct(product_id, name, price, quantity, threshold)
        if category in (ProductEnum.FURNITURE, ProductEnum.OTHER):
            product = Product()
            product.set_id(product_id)
            product.set_name(name)
            product.set_price(price)
            product.set_quantity(quantity)
            product.set_threshold(threshold)
            product.set_category(category)
            return product
        raise ValueError(f"Unsupported product category: {category}")
