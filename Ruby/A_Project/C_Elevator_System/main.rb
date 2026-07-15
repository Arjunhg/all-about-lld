require_relative 'CommonEnums/direction'
require_relative 'ObserverPattern/elevator_display'
require_relative 'SchedulingStrategy/ConcreteStrategies/fcfs_scheduling_strategy'
require_relative 'SchedulingStrategy/ConcreteStrategies/look_scheduling_strategy'
require_relative 'SchedulingStrategy/ConcreteStrategies/scan_scheduling_strategy'
require_relative 'UtilityClass/builder'

def display_elevator_status(elevators)
  puts "Elevator Status:"
  elevators.each do |elevator|
    destinations = elevator.get_destination_floors.map(&:get_floor)
    puts "Elevator #{elevator.get_elevator_id} - Floor: #{elevator.get_current_floor}, " \
         "State: #{elevator.get_state}, Destination: #{destinations}"
  end
end

def main
  building   = Building.new("Skyline Tower", 10, 3)
  controller = building.get_elevator_controller

  display = ElevatorDisplay.new
  controller.get_elevators.each { |e| e.add_observer(display) }

  puts "Welcome to the Elevator System CLI! Type 'exit' (or choose 5) to quit."
  puts "Building: #{building.get_name}, Floors: #{building.get_number_of_floors}, " \
       "Elevators: #{controller.get_elevators.size}"

  min_floor         = 0
  max_floor         = building.get_number_of_floors - 1
  valid_elevator_ids = controller.get_elevators.map(&:get_elevator_id).to_set

  loop do
    puts "\nSelect an option (numeric):"
    puts "1. Request Elevator (External)"
    puts "2. Request Floor (Internal)"
    puts "3. Simulate next step"
    puts "4. Change Scheduling Strategy"
    puts "5. Exit"

    raw_choice = $stdin.gets&.strip&.downcase
    if raw_choice.nil? || raw_choice == "exit"
      puts "Exiting Elevator System CLI. Goodbye!"
      break
    end

    choice = Integer(raw_choice, 10) rescue nil
    unless choice
      puts "Invalid choice. Please try again."
      next
    end

    case choice
    when 1
      print "Enter elevator ID: "
      elevator_id = Integer($stdin.gets&.strip, 10) rescue nil
      print "Enter floor number: "
      floor_number = Integer($stdin.gets&.strip, 10) rescue nil
      print "Enter direction (1 for UP or 2 for DOWN): "
      dir_input = Integer($stdin.gets&.strip, 10) rescue nil

      unless elevator_id && floor_number && dir_input
        puts "Invalid input. Please enter numeric values."
        next
      end
      unless valid_elevator_ids.include?(elevator_id)
        puts "Invalid elevator ID."
        next
      end
      unless (min_floor..max_floor).include?(floor_number)
        puts "Invalid floor. Enter a floor between #{min_floor} and #{max_floor}."
        next
      end
      unless [1, 2].include?(dir_input)
        puts "Invalid direction. Enter 1 for UP or 2 for DOWN."
        next
      end

      direction = dir_input == 1 ? Direction::UP : Direction::DOWN
      controller.set_current_elevator_id(elevator_id)
      controller.request_elevator(elevator_id, floor_number, direction)

    when 2
      print "Enter elevator ID: "
      elevator_id = Integer($stdin.gets&.strip, 10) rescue nil
      print "Enter destination floor number: "
      floor_number = Integer($stdin.gets&.strip, 10) rescue nil

      unless elevator_id && floor_number
        puts "Invalid input. Please enter numeric values."
        next
      end
      unless valid_elevator_ids.include?(elevator_id)
        puts "Invalid elevator ID."
        next
      end
      unless (min_floor..max_floor).include?(floor_number)
        puts "Invalid floor. Enter a floor between #{min_floor} and #{max_floor}."
        next
      end

      controller.set_current_elevator_id(elevator_id)
      controller.request_floor(elevator_id, floor_number)

    when 3
      puts "Simulating next step..."
      controller.step
      display_elevator_status(controller.get_elevators)

    when 4
      puts "Select Scheduling Strategy:"
      puts "1. First-Come-First-Serve (FCFS)"
      puts "2. SCAN"
      puts "3. LOOK"
      print "Enter strategy: "
      strategy_choice = Integer($stdin.gets&.strip, 10) rescue nil

      case strategy_choice
      when 1
        controller.set_scheduling_strategy(FCFSSchedulingStrategy.new)
        puts "Scheduling Strategy set to FCFS"
      when 2
        controller.set_scheduling_strategy(ScanSchedulingStrategy.new)
        puts "Scheduling Strategy set to SCAN"
      when 3
        controller.set_scheduling_strategy(LookSchedulingStrategy.new)
        puts "Scheduling Strategy set to LOOK"
      else
        puts "Invalid choice. Please try again."
      end

    when 5
      puts "Exiting Elevator System CLI. Goodbye!"
      break

    else
      puts "Invalid choice. Please try again."
    end
  end

  puts "Elevator System CLI terminated."
end

main
