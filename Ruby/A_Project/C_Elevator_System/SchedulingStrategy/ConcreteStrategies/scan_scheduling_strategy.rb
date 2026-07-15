require_relative '../scheduling_strategy'
require_relative '../../CommonEnums/direction'

# SCAN scheduling: services all requests in one direction, then reverses
class ScanSchedulingStrategy < SchedulingStrategy
  def get_next_stop(elevator)
    elevator_direction = elevator.get_direction
    current_floor      = elevator.get_current_floor
    requests           = elevator.get_request_queue

    if requests.empty?
      elevator.set_direction(Direction::IDLE)
      return current_floor
    end

    # Partition floors into above and below current floor
    up_floors   = requests.map(&:get_floor).select { |f| f > current_floor }.sort
    down_floors = requests.map(&:get_floor).select { |f| f <= current_floor }.sort.reverse

    if elevator_direction == Direction::IDLE
      nearest_up   = up_floors.first
      nearest_down = down_floors.first

      if nearest_up.nil?
        elevator.set_direction(Direction::DOWN)
        return nearest_down || current_floor
      end

      if nearest_down.nil?
        elevator.set_direction(Direction::UP)
        return nearest_up
      end

      if (nearest_up - current_floor) <= (current_floor - nearest_down)
        elevator.set_direction(Direction::UP)
        return up_floors.shift
      else
        elevator.set_direction(Direction::DOWN)
        return down_floors.shift
      end
    end

    if elevator_direction == Direction::UP
      return up_floors.shift unless up_floors.empty?

      switch_direction(elevator, down_floors)
    else
      return down_floors.shift unless down_floors.empty?

      switch_direction(elevator, up_floors)
    end
  end

  private

  def switch_direction(elevator, opposite_floors)
    new_direction = elevator.get_direction == Direction::UP ? Direction::DOWN : Direction::UP
    elevator.set_direction(new_direction)

    return elevator.get_current_floor if opposite_floors.empty?

    # Return the closest floor in the new direction
    if new_direction == Direction::DOWN
      opposite_floors.sort.reverse.first
    else
      opposite_floors.sort.first
    end
  end
end
