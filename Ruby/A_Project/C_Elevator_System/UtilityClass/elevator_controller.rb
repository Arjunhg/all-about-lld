require_relative '../CommandPattern/ConcreteClasses/elevator_request'
require_relative '../CommonEnums/direction'
require_relative '../SchedulingStrategy/ConcreteStrategies/scan_scheduling_strategy'
require_relative 'elevator'
require_relative 'floor'

class ElevatorController
  def initialize(number_of_elevators, number_of_floors)
    @elevators           = Array.new(number_of_elevators) { |i| Elevator.new(i) }
    @floors              = Array.new(number_of_floors)    { |i| Floor.new(i) }
    @scheduling_strategy = ScanSchedulingStrategy.new
    @current_elevator_id = 0
  end

  def set_scheduling_strategy(strategy)
    @scheduling_strategy = strategy
  end

  # External request: someone on a floor calls the elevator
  def request_elevator(elevator_id, floor_number, direction)
    puts "External request: Requesting elevator #{elevator_id} to floor #{floor_number} going #{direction}"
    elevator = get_elevator_by_id(elevator_id)

    if elevator
      elevator.add_request(ElevatorRequest.new(elevator_id, floor_number, false, direction))
    else
      puts "ElevatorController: No elevator found with ID #{elevator_id}"
    end
  end

  # Internal request: passenger inside the elevator presses a floor button
  def request_floor(elevator_id, floor_number)
    elevator = get_elevator_by_id(elevator_id)

    unless elevator
      puts "ElevatorController: No elevator found with ID #{elevator_id}"
      return
    end

    puts "ElevatorController, Internal request: Elevator #{elevator_id} requested to go to floor #{floor_number}"

    direction = floor_number > elevator.get_current_floor ? Direction::UP : Direction::DOWN
    elevator.add_request(ElevatorRequest.new(elevator_id, floor_number, true, direction))
  end

  # Advance every elevator one step toward its next scheduled stop
  def step
    @elevators.each do |elevator|
      next if elevator.get_request_queue.empty?

      next_stop = @scheduling_strategy.get_next_stop(elevator)
      if elevator.get_current_floor != next_stop
        elevator.move_to_next_stop(next_stop)
      else
        elevator.complete_request
      end
    end
  end

  def get_elevators
    @elevators
  end

  def get_floors
    @floors
  end

  def set_current_elevator_id(elevator_id)
    @current_elevator_id = elevator_id
  end

  private

  def get_elevator_by_id(elevator_id)
    @elevators.find { |e| e.get_elevator_id == elevator_id }
  end
end
