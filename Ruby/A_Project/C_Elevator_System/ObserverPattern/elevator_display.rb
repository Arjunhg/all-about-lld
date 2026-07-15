require_relative 'elevator_observer'

# Concrete observer that prints elevator state and floor changes to stdout
class ElevatorDisplay < ElevatorObserver
  def on_elevator_state_change(elevator, state)
    puts "Elevator #{elevator.get_elevator_id} changed state to #{state}"
  end

  def on_elevator_floor_change(elevator, floor)
    puts "Elevator #{elevator.get_elevator_id} arrived at floor #{floor}"
  end
end
