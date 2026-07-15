require_relative '../scheduling_strategy'
require_relative '../../CommonEnums/direction'

# LOOK scheduling: like SCAN but only goes as far as the last request in each direction.
# Travel direction is driven by the elevator's current movement direction so an older
# queued request on the opposite side cannot flip the active sweep prematurely.
# The direction only reverses when no more stops remain on the current side.
class LookSchedulingStrategy < SchedulingStrategy
  def get_next_stop(elevator)
    current_floor    = elevator.get_current_floor
    requests         = elevator.get_request_queue
    elevator_dir     = elevator.get_direction

    return current_floor if requests.empty?

    # Derive travel direction from the elevator's live direction.
    # Fall back to the first queued request only when the elevator is IDLE.
    travel_direction =
      if elevator_dir == Direction::UP || elevator_dir == Direction::DOWN
        elevator_dir
      else
        # IDLE: pick direction toward the first queued request
        first_floor = requests.first.get_floor
        if first_floor > current_floor
          Direction::UP
        elsif first_floor < current_floor
          Direction::DOWN
        else
          return current_floor
        end
      end

    # Collect eligible stops in the current sweep direction
    candidate = find_candidate(requests, current_floor, travel_direction)
    return candidate if candidate

    # No stops remain on this side — reverse and try the other direction
    reverse_dir = travel_direction == Direction::UP ? Direction::DOWN : Direction::UP
    elevator.set_direction(reverse_dir)
    find_candidate(requests, current_floor, reverse_dir) || current_floor
  end

  private

  # Returns the nearest eligible floor in travel_direction from current_floor,
  # respecting internal-request and directional-match rules.
  def find_candidate(requests, current_floor, travel_direction)
    candidate = nil

    requests.each do |req|
      req_floor = req.get_floor

      if travel_direction == Direction::UP && req_floor > current_floor
        if req.check_is_internal_request || req.get_direction == Direction::UP
          candidate = req_floor if candidate.nil? || req_floor < candidate
        end
      elsif travel_direction == Direction::DOWN && req_floor < current_floor
        if req.check_is_internal_request || req.get_direction == Direction::DOWN
          candidate = req_floor if candidate.nil? || req_floor > candidate
        end
      end
    end

    candidate
  end
end
