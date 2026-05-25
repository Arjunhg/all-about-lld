from CommonEnums.direction import Direction
from ObserverPattern.elevator_display import ElevatorDisplay
from SchedulingStrategy.ConcreteStrategies.fcfs_scheduling_strategy import (
	FCFSSchedulingStrategy,
)
from SchedulingStrategy.ConcreteStrategies.look_scheduling_strategy import (
	LookSchedulingStrategy,
)
from SchedulingStrategy.ConcreteStrategies.scan_scheduling_strategy import (
	ScanSchedulingStrategy,
)
from UtilityClasses.building import Building


def display_elevator_status(elevators):
	print("Elevator Status:")
	for elevator in elevators:
		print(
			f"Elevator {elevator.get_elevator_id()} - Floor: "
			f"{elevator.get_current_floor()}, State: {elevator.get_state()}, "
			f"Destination: {elevator.get_destination_floors()}"
		)


def main():
	building = Building("Skyline Tower", 10, 3)
	controller = building.get_elevator_controller()

	display = ElevatorDisplay()
	for elevator in controller.get_elevators():
		elevator.add_observer(display)

	print("Welcome to the Elevator System CLI! Type 'exit' (or choose 5) to quit.")
	print(
		f"Building: {building.get_name()}, Floors: {building.get_number_of_floors()}, "
		f"Elevators: {len(controller.get_elevators())}"
	)
	min_floor = 0
	max_floor = building.get_number_of_floors() - 1
	valid_elevator_ids = {elevator.get_elevator_id() for elevator in controller.get_elevators()}

	running = True
	while running:
		print("Select an option (numeric):")
		print("1. Request Elevator (External)")
		print("2. Request Floor (Internal)")
		print("3. Simulate next step")
		print("4. Change Scheduling Strategy")
		print("5. Exit")

		try:
			raw_choice = input("Enter choice: ").strip().lower()
			if raw_choice == "exit":
				running = False
				print("Exiting Elevator System CLI. Goodbye!")
				continue
			choice = int(raw_choice)
		except ValueError:
			print("Invalid choice. Please try again.")
			continue

		if choice == 1:
			try:
				elevator_id = int(input("Enter elevator ID: ").strip())
				floor_number = int(input("Enter floor number: ").strip())
				dir_input = int(input("Enter direction (1 for UP or 2 for DOWN): ").strip())
			except ValueError:
				print("Invalid input. Please enter numeric values.")
				continue
			if elevator_id not in valid_elevator_ids:
				print("Invalid elevator ID.")
				continue
			if not (min_floor <= floor_number <= max_floor):
				print(f"Invalid floor. Enter a floor between {min_floor} and {max_floor}.")
				continue
			if dir_input not in (1, 2):
				print("Invalid direction. Enter 1 for UP or 2 for DOWN.")
				continue

			direction = Direction.UP if dir_input == 1 else Direction.DOWN
			controller.set_current_elevator_id(elevator_id)
			controller.request_elevator(elevator_id, floor_number, direction)
		elif choice == 2:
			try:
				elevator_id = int(input("Enter elevator ID: ").strip())
				floor_number = int(input("Enter destination floor number: ").strip())
			except ValueError:
				print("Invalid input. Please enter numeric values.")
				continue
			if elevator_id not in valid_elevator_ids:
				print("Invalid elevator ID.")
				continue
			if not (min_floor <= floor_number <= max_floor):
				print(f"Invalid floor. Enter a floor between {min_floor} and {max_floor}.")
				continue

			controller.set_current_elevator_id(elevator_id)
			controller.request_floor(elevator_id, floor_number)
		elif choice == 3:
			print("Simulating next step...")
			controller.step()
			display_elevator_status(controller.get_elevators())
		elif choice == 4:
			print("Select Scheduling Strategy:")
			print("1. First-Come-First-Serve (FCFS)")
			print("2. (SCAN)")
			print("3. (LOOK)")
			try:
				strategy_choice = int(input("Enter strategy: ").strip())
			except ValueError:
				print("Invalid choice. Please try again.")
				continue

			if strategy_choice == 1:
				controller.set_scheduling_strategy(FCFSSchedulingStrategy())
				print("Scheduling Strategy set to FCFS")
			elif strategy_choice == 2:
				controller.set_scheduling_strategy(ScanSchedulingStrategy())
				print("Scheduling Strategy set to SCAN")
			elif strategy_choice == 3:
				controller.set_scheduling_strategy(LookSchedulingStrategy())
				print("Scheduling Strategy set to LOOK")
			else:
				print("Invalid choice. Please try again.")
		elif choice == 5:
			running = False
			print("Exiting Elevator System CLI. Goodbye!")
		else:
			print("Invalid choice. Please try again.")

	print("Elevator System CLI terminated.")


if __name__ == "__main__":
	main()
