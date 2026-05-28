from ProductReplenishmentStrategies.replenishment_strategy import ReplenishmentStrategy


class JustInTimeStrategy(ReplenishmentStrategy):
    def replenish(self, product):
        current_quantity = product.get_quantity()
        threshold = product.get_threshold()
        if current_quantity < threshold:
            product.set_quantity(threshold)
        print(
            f"Replenishing product {product.get_name()} using Just-In-Time Strategy."
        )
