from CommonEnums.coin import Coin
from PaymentStrategy.payment_strategy import PaymentStrategy
from UtilityClasses.inventory import Inventory
from VendingMachineStates.ConcreteStates.idle_state import IdleState


class VendingMachineContext:
    def __init__(self):
        self._curr_machine_state = IdleState()
        self._payment_strategy = None
        self._inventory = Inventory(10)
        self._coin_list = []
        self._selected_item_code = 0
        print("Initialized Vending Machine in Idle State")

    def set_state(self, new_state):
        self._curr_machine_state = new_state

    def get_curr_machine_state(self):
        return self._curr_machine_state

    def set_payment_strategy(self, payment_strategy: PaymentStrategy):
        self._payment_strategy = payment_strategy

    def insert_coin(self, coin: Coin):
        self._curr_machine_state.insert_coin(self, coin)

    def select_item(self, code_number: int):
        self._curr_machine_state.select_item(self, code_number)

    def dispense(self):
        self._curr_machine_state.dispense_item(self)

    def process_payment(self, amount: int) -> bool:
        if self._payment_strategy is None:
            raise RuntimeError("Payment strategy not set")
        return self._payment_strategy.process_payment(amount)

    def get_inventory(self) -> Inventory:
        return self._inventory

    def get_coin_list(self):
        return self._coin_list

    def add_coin(self, coin: Coin):
        self._coin_list.append(coin)

    def get_balance(self) -> int:
        return sum(coin.get_value() for coin in self._coin_list)

    def clear_balance(self):
        self._coin_list.clear()

    def refund_payment(self):
        balance = self.get_balance()
        if balance > 0:
            print(f"Refunding amount: {balance}")
        elif self._payment_strategy is not None:
            print("Voiding card payment.")
        self.clear_balance()

    def get_selected_item_code(self) -> int:
        return self._selected_item_code

    def set_selected_item_code(self, code_number: int):
        self._selected_item_code = code_number

    def reset_selection(self):
        self._selected_item_code = 0
