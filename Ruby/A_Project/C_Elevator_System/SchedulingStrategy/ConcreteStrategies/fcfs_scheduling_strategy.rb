require_relative '../scheduling_strategy'
require_relative '../../CommonEnums/direction'

# First-Come-First-Serve scheduling: always serves the oldest request first
class FCFSSchedulingStrategy < SchedulingStrategy
  def get_next_stop(elevator)
    current_floor  = elevator.get_current_floor
    request_queue  = elevator.get_request_queue

    if request_queue.empty?
      elevator.set_direction(Direction::IDLE)
      return current_floor
    end

    target_floor = request_queue.first.get_floor

    if target_floor > current_floor
      elevator.set_direction(Direction::UP)
    elsif target_floor < current_floor
      elevator.set_direction(Direction::DOWN)
    else
      elevator.set_direction(Direction::IDLE)
    end

    target_floor
  end
end
