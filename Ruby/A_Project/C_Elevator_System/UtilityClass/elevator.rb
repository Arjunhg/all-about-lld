require_relative '../CommonEnums/direction'
require_relative '../CommonEnums/elevator_state'

class Elevator
  def initialize(elevator_id)
    @id            = elevator_id
    @current_floor = 0
    @direction     = Direction::IDLE
    @state         = ElevatorState::IDLE
    @observers     = []
    @requests      = []
  end

  # ── Observer management ────────────────────────────────────────────────────

  def add_observer(observer)
    @observers << observer
  end

  def remove_observer(observer)
    @observers.delete(observer)
  end

  def notify_state_change
    @observers.each { |obs| obs.on_elevator_state_change(self, @state) }
  end

  def notify_floor_change(floor)
    @observers.each { |obs| obs.on_elevator_floor_change(self, floor) }
  end

  # ── Direction / State ──────────────────────────────────────────────────────

  def get_direction
    @direction
  end

  def set_direction(new_direction)
    @direction = new_direction
  end

  def get_state
    @state
  end

  def set_state(new_state)
    @state = new_state
    notify_state_change
  end

  # ── Request management ─────────────────────────────────────────────────────

  def add_request(request)
    @requests << request unless @requests.include?(request)

    requested_floor = request.get_floor

    if @state == ElevatorState::IDLE
      if requested_floor == @current_floor
        complete_arrival
        return
      end

      set_direction(requested_floor > @current_floor ? Direction::UP : Direction::DOWN)
      set_state(ElevatorState::MOVING)
    end
  end

  def move_to_next_stop(next_stop)
    return unless @state == ElevatorState::MOVING

    if next_stop == @current_floor
      complete_arrival
      return
    end

    set_direction(next_stop > @current_floor ? Direction::UP : Direction::DOWN)

    until @current_floor == next_stop
      @current_floor += (@direction == Direction::UP ? 1 : -1)
      notify_floor_change(@current_floor)
    end

    complete_arrival
  end

  def complete_request
    complete_arrival
  end

  # ── Accessors ──────────────────────────────────────────────────────────────

  def get_elevator_id
    @id
  end

  def get_current_floor
    @current_floor
  end

  def get_request_queue
    @requests.dup
  end

  def get_destination_floors
    @requests.dup
  end

  private

  def complete_arrival
    set_state(ElevatorState::STOPPED)
    @requests.reject! { |req| req.get_floor == @current_floor }

    if @requests.empty?
      set_direction(Direction::IDLE)
      set_state(ElevatorState::IDLE)
    else
      set_state(ElevatorState::MOVING)
    end
  end
end
