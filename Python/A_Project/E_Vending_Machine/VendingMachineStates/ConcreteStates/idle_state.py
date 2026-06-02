from VendingMachineStates.vending_machine_state import VendingMachineState


class IdleState(VendingMachineState):
    def get_state_name(self) -> str:
        return "Idle State"

    def insert_coin(self, context, coin):
        # To handle circular import issue, we import IdleState here instead of at the top
        from VendingMachineStates.ConcreteStates.has_money_state import HasMoneyState

        context.add_coin(coin)
        context.set_state(HasMoneyState())

    def select_item(self, context, item_code: int):
        raise RuntimeError("Insert money first")

    def dispense_item(self, context):
        raise RuntimeError("Nothing to dispense in Idle State")
