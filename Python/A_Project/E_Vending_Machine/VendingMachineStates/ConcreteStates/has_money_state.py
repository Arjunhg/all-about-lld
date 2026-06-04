from CommonExceptions.invalid_shelf_code_exception import InvalidShelfCodeException
from CommonExceptions.item_sold_out_exception import ItemSoldOutException
from VendingMachineStates.vending_machine_state import VendingMachineState


class HasMoneyState(VendingMachineState):
    def get_state_name(self) -> str:
        return "Has Money State"

    def insert_coin(self, context, coin):
        context.add_coin(coin)

    def select_item(self, context, item_code: int):
        # To handle circular import issue, we import IdleState here instead of at the top
        from VendingMachineStates.ConcreteStates.dispense_state import DispenseState
        from VendingMachineStates.ConcreteStates.idle_state import IdleState

        try:
            item = context.get_inventory().get_item(item_code)
        except InvalidShelfCodeException as exc:
            context.refund_payment()
            context.reset_selection()
            context.set_state(IdleState())
            raise ValueError(f"Invalid item code selected: {item_code}") from exc
        except ItemSoldOutException as exc:
            context.refund_payment()
            context.reset_selection()
            context.set_state(IdleState())
            raise RuntimeError(f"Selected item is out of stock: {item_code}") from exc

        paid = context.process_payment(item.get_price())
        if not paid:
            context.refund_payment()
            context.reset_selection()
            context.set_state(IdleState())
            raise RuntimeError(
                "Insufficient payment. Please insert more coins or choose a "
                "different payment method."
            )

        context.set_selected_item_code(item_code)
        context.set_state(DispenseState())

    def dispense_item(self, context):
        raise RuntimeError("Select item before dispensing")
