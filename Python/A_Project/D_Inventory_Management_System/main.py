from CommonEnums.product_enum import ProductEnum
from InventoryStockManager.inventory_manager import InventoryManager
from ProductFactory.product_factory import ProductFactory
from ProductReplenishmentStrategies.ConcreteReplenishmentStrategies.bulk_order_strategy import (
	BulkOrderStrategy,
)
from ProductReplenishmentStrategies.ConcreteReplenishmentStrategies.just_in_time_strategy import (
	JustInTimeStrategy,
)
from UtilityClasses.warehouse import Warehouse


def main():
	replenishment_strategy = JustInTimeStrategy()
	inventory_manager = InventoryManager.get_instance(replenishment_strategy)

	product_factory = ProductFactory()
	laptop = product_factory.create_product(
		ProductEnum.ELECTRONICS, "L1", "Laptop", 100.0, 50, 25
	)
	t_shirt = product_factory.create_product(
		ProductEnum.CLOTHING, "TS1", "T-Shirt", 20.0, 200, 100
	)
	apple = product_factory.create_product(
		ProductEnum.GROCERY, "A1", "Apple", 1.0, 100, 200
	)

	w1 = Warehouse("Warehouse 1")
	w2 = Warehouse("Warehouse 2")
	inventory_manager.add_warehouse(w1)
	inventory_manager.add_warehouse(w2)

	w1.add_product(laptop, 15)
	w1.add_product(t_shirt, 20)
	w2.add_product(apple, 50)

	inventory_manager.set_replenishment_strategy(JustInTimeStrategy())
	inventory_manager.perform_inventory_check()

	inventory_manager.set_replenishment_strategy(BulkOrderStrategy())
	inventory_manager.check_and_replenish_stock("L1")


if __name__ == "__main__":
	main()
