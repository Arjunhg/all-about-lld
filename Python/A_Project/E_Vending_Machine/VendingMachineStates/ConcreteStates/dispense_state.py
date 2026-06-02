from CommonExceptions.invalid_shelf_code_exception import InvalidShelfCodeException
from CommonExceptions.item_sold_out_exception import ItemSoldOutException
from VendingMachineStates.ConcreteStates.out_of_stock_state import OutOfStockState
from VendingMachineStates.vending_machine_state import VendingMachineState


class DispenseState(VendingMachineState):
    def get_state_name(self) -> str:
        return "Dispense State"

    def insert_coin(self, context, coin):
        raise RuntimeError("Cannot insert coin while dispensing item")

    def select_item(self, context, item_code: int):
        raise RuntimeError("Cannot select item while dispensing another item")

    def dispense_item(self, context):
        # To handle circular import issue, we import IdleState here instead of at the top
        from VendingMachineStates.ConcreteStates.idle_state import IdleState

        code = context.get_selected_item_code()
        try:
            context.get_inventory().remove_item(code)
        except InvalidShelfCodeException as exc:
            raise RuntimeError(f"Invalid shelf code during dispense: {code}") from exc
        except ItemSoldOutException as exc:
            context.set_state(OutOfStockState())
            raise RuntimeError(f"Item sold out during dispense: {code}") from exc

        context.clear_balance()
        context.reset_selection()
        context.set_state(IdleState())
