require_relative '../scheduling_strategy'
require_relative '../../CommonEnums/direction'

# LOOK scheduling: like SCAN but only goes as far as the last request in each direction
class LookSchedulingStrategy < SchedulingStrategy
  def get_next_stop(elevator)
    current_floor = elevator.get_current_floor
    requests      = elevator.get_request_queue

    return current_floor if requests.empty?

    next_floor = requests.first.get_floor

    if next_floor > current_floor
      travel_direction = Direction::UP
    elsif next_floor < current_floor
      travel_direction = Direction::DOWN
    else
      return current_floor
    end

    candidate = nil

    requests.each do |req|
      req_floor = req.get_floor

      if travel_direction == Direction::UP && current_floor < req_floor && req_floor <= next_floor
        if req.check_is_internal_request || req.get_direction == Direction::UP
          candidate = req_floor if candidate.nil? || req_floor < candidate
        end
      elsif travel_direction == Direction::DOWN && next_floor <= req_floor && req_floor < current_floor
        if req.check_is_internal_request || req.get_direction == Direction::DOWN
          candidate = req_floor if candidate.nil? || req_floor > candidate
        end
      end
    end

    candidate || next_floor
  end
end
