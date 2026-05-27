from ProductFactory.product_factory import ProductFactory


class InventoryManager:
    _instance = None

    def __init__(self, replenishment_strategy):
        self.warehouses = []
        self.product_factory = ProductFactory()
        self.replenishment_strategy = replenishment_strategy

    @classmethod
    def get_instance(cls, replenishment_strategy):
        if cls._instance is None:
            cls._instance = cls(replenishment_strategy)
        return cls._instance

    def set_replenishment_strategy(self, replenishment_strategy):
        self.replenishment_strategy = replenishment_strategy

    def add_warehouse(self, warehouse):
        self.warehouses.append(warehouse)

    def remove_warehouse(self, warehouse):
        if warehouse in self.warehouses:
            self.warehouses.remove(warehouse)

    def get_product_by_id(self, product_id):
        for warehouse in self.warehouses:
            product = warehouse.get_product_by_id(product_id)
            if product is not None:
                return product
        return None

    def check_and_replenish_stock(self, product_id):
        product = self.get_product_by_id(product_id)
        if product is not None:
            if product.get_quantity() < product.get_threshold():
                if self.replenishment_strategy is not None:
                    self.replenishment_strategy.replenish(product)

    def perform_inventory_check(self):
        for warehouse in self.warehouses:
            for product in warehouse.get_all_products():
                if product.get_quantity() < product.get_threshold():
                    if self.replenishment_strategy is not None:
                        self.replenishment_strategy.replenish(product)
