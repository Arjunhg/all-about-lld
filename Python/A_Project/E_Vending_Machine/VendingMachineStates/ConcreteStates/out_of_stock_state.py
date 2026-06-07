from VendingMachineStates.vending_machine_state import VendingMachineState


class OutOfStockState(VendingMachineState):
    def get_state_name(self) -> str:
        return "Out Of Stock State"

    def insert_coin(self, context, coin):
        raise RuntimeError("Machine is out of stock")

    def select_item(self, context, code_number: int):
        raise RuntimeError("Machine is out of stock")

    def dispense_item(self, context):
        raise RuntimeError("Machine is out of stock")
