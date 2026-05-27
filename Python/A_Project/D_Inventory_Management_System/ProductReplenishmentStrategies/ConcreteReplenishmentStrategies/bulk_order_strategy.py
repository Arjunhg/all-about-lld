from ProductReplenishmentStrategies.replenishment_strategy import ReplenishmentStrategy


class BulkOrderStrategy(ReplenishmentStrategy):
    def replenish(self, product):
        print(
            f"Replenishing product {product.get_name()} using Bulk Order Strategy."
        )
