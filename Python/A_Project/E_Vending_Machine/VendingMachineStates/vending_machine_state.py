from abc import ABC, abstractmethod


class VendingMachineState(ABC):
    @abstractmethod
    def get_state_name(self) -> str:
        pass

    @abstractmethod
    def insert_coin(self, context, coin):
        pass

    @abstractmethod
    def select_item(self, context, item_code: int):
        pass

    @abstractmethod
    def dispense_item(self, context):
        pass
