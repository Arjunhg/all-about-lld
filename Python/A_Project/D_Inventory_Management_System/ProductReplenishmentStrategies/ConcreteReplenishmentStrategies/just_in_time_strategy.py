from ProductReplenishmentStrategies.replenishment_strategy import ReplenishmentStrategy


class JustInTimeStrategy(ReplenishmentStrategy):
    def replenish(self, product):
        print(
            f"Replenishing product {product.get_name()} using Just-In-Time Strategy."
        )
