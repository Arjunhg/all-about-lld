from ProductReplenishmentStrategies.replenishment_strategy import ReplenishmentStrategy


class BulkOrderStrategy(ReplenishmentStrategy):
    def replenish(self, product):
        target_quantity = product.get_threshold() * 2
        if product.get_quantity() < target_quantity:
            product.set_quantity(target_quantity)
        print(
            f"Replenishing product {product.get_name()} using Bulk Order Strategy."
        )
