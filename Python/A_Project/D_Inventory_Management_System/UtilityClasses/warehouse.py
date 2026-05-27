class Warehouse:
    def __init__(self, name: str):
        self.name = name
        self.location = None
        self.products = {}

    def set_location(self, location: str):
        self.location = location

    def add_product(self, product, quantity: int):
        product_id = product.get_id()
        if product_id in self.products:
            existing_product = self.products[product_id]
            existing_product.set_quantity(existing_product.get_quantity() + quantity)
        else:
            product.set_quantity(quantity)
        self.products[product_id] = product
        print(
            f"Added {quantity} of product ID: {product_id} to warehouse: {self.name}. "
            f"New quantity: {self.get_available_quantity(product_id)}"
        )

    def remove_product(self, product_id: str, quantity: int) -> bool:
        if product_id in self.products:
            product = self.products[product_id]
            current_quantity = product.get_quantity()
            if current_quantity >= quantity:
                product.set_quantity(current_quantity - quantity)
                print(
                    f"Removed {quantity} of product ID: {product_id} from warehouse: "
                    f"{self.name}. Remaining Quantity: {product.get_quantity()}"
                )
                if product.get_quantity() == 0:
                    self.products.pop(product_id, None)
                    print(
                        f"Product ID: {product_id} is out of stock and removed from "
                        f"warehouse: {self.name}"
                    )
                return True
            print(
                f"Insufficient stock for product ID: {product_id} in warehouse: "
                f"{self.name}. Available: {current_quantity}, Requested: {quantity}"
            )
            return False
        print(f"Product ID: {product_id} not found in warehouse: {self.name}")
        return False

    def get_available_quantity(self, product_id: str) -> int:
        if product_id in self.products:
            return self.products[product_id].get_quantity()
        return 0

    def get_product_by_id(self, product_id: str):
        return self.products.get(product_id)

    def get_all_products(self):
        return self.products.values()
