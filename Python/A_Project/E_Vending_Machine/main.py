from CommonEnums.coin import Coin
from CommonEnums.item_type import ItemType
from PaymentStrategy.ConcreteStrategy.card_payment_strategy import CardPaymentStrategy
from PaymentStrategy.ConcreteStrategy.coin_payment_strategy import CoinPaymentStrategy
from UtilityClasses.item import Item
from VendingMachineStates.vending_machine_context import VendingMachineContext


def populate_inventory(vending_machine: VendingMachineContext):
	for index in range(10):
		new_item = Item()
		code_number = 101 + index

		if 0 <= index < 3:
			new_item.set_type(ItemType.COKE)
			new_item.set_price(12)
		elif 3 <= index < 5:
			new_item.set_type(ItemType.PEPSI)
			new_item.set_price(9)
		elif 5 <= index < 8:
			new_item.set_type(ItemType.SODA)
			new_item.set_price(15)
		else:
			new_item.set_type(ItemType.JUICE)
			new_item.set_price(10)

		for _ in range(5):
			vending_machine.get_inventory().add_item(new_item, code_number)


def show_inventory(vending_machine: VendingMachineContext):
	print("Current Inventory:")
	slots = vending_machine.get_inventory().get_inventory()
	for code, shelf in slots.items():
		if shelf.is_sold_out():
			print(f"Code: {code} | SOLD OUT")
		else:
			item = shelf.get_items()[0]
			print(
				f"Code: {code} | Item: {item.get_type()} | Price: {item.get_price()} "
				f"| Quantity: {len(shelf.get_items())}"
			)


def insert_coins_for_amount(vending_machine: VendingMachineContext, amount: int):
	remaining = amount
	for coin in (Coin.TEN_RUPEE, Coin.FIVE_RUPEE, Coin.TWO_RUPEE, Coin.ONE_RUPEE):
		while remaining >= coin.get_value():
			vending_machine.insert_coin(coin)
			remaining -= coin.get_value()


def main():
	vending_machine = VendingMachineContext()
	initialized = False

	running = True
	while running:
		try:
			print("|")
			print("Vending Machine is ready for operations")
			print("|")

			if not initialized:
				populate_inventory(vending_machine)
				initialized = True
			show_inventory(vending_machine)

			print("|")
			print("Select Payment Method: ")
			print("1. Coin Payment")
			print("2. Card Payment")
			print("Enter your choice: ")

			payment_choice = input().strip()
			if payment_choice.lower() in ("exit", "q"):
				print("Exiting Vending Machine.")
				break

			if payment_choice == "1":
				print("Using Coin Payment Method")
				raw_code = input(
					"Enter item code to select item (or type 'exit'): "
				).strip()
				if raw_code.lower() in ("exit", "q"):
					print("Exiting Vending Machine.")
					break
				item_code = int(raw_code)
				item = vending_machine.get_inventory().get_item(item_code)
				vending_machine.clear_balance()
				vending_machine.set_payment_strategy(
					CoinPaymentStrategy(vending_machine.get_coin_list())
				)
				insert_coins_for_amount(vending_machine, item.get_price())
				vending_machine.select_item(item_code)
				vending_machine.dispense()
				show_inventory(vending_machine)
			elif payment_choice == "2":
				print("Using Card Payment Method")
				card_number = input("Enter card number: ").strip()
				expiry_date = input("Enter expiry date (MM/YY): ").strip()

				vending_machine.set_payment_strategy(
					CardPaymentStrategy(card_number, expiry_date)
				)
				print("|")
				raw_code = input(
					"Enter item code to select item (or type 'exit'): "
				).strip()
				if raw_code.lower() in ("exit", "q"):
					print("Exiting Vending Machine.")
					break
				item_code = int(raw_code)
				vending_machine.select_item(item_code)
				vending_machine.dispense()
				show_inventory(vending_machine)
			else:
				print("Invalid payment choice. Please try again.")
				continue

			continue_choice = input("Another transaction? (y/n): ").strip().lower()
			if continue_choice in ("n", "no", "exit", "q"):
				print("Exiting Vending Machine.")
				break
		except EOFError:
			print("\nExiting Vending Machine.")
			break
		except Exception as exc:
			print(f"Error: {exc}")
			from VendingMachineStates.ConcreteStates.idle_state import IdleState

			vending_machine.clear_balance()
			vending_machine.reset_selection()
			vending_machine.set_state(IdleState())
			show_inventory(vending_machine)


if __name__ == "__main__":
	main()
