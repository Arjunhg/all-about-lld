from ObserverPattern.elevator_observer import ElevatorObserver


class ElevatorDisplay(ElevatorObserver):
    def on_elevator_state_change(self, elevator, state):
        state_name = getattr(state, "name", str(state))
        print(f"Elevator {elevator.get_elevator_id()} changed state to {state_name}")

    def on_elevator_floor_change(self, elevator, floor: int):
        print(f"Elevator {elevator.get_elevator_id()} arrived at floor {floor}")
