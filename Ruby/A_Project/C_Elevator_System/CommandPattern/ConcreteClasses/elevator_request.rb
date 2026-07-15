require_relative '../elevator_command'
require_relative '../../CommonEnums/direction'

# Concrete command representing a single elevator request (internal or external)
class ElevatorRequest < ElevatorCommand
  def initialize(elevator_id, floor, is_internal, direction, elevator_controller = nil)
    @elevator_id          = elevator_id
    @floor                = floor
    @is_internal_request  = is_internal
    @request_direction    = direction
    @elevator_controller  = elevator_controller
  end

  def execute
    return if @elevator_controller.nil?

    if @is_internal_request
      @elevator_controller.request_floor(@elevator_id, @floor)
    else
      @elevator_controller.request_elevator(@elevator_id, @floor, @request_direction)
    end
  end

  def get_direction
    @request_direction
  end

  def check_is_internal_request
    @is_internal_request
  end

  def get_floor
    @floor
  end

  def ==(other)
    return false unless other.is_a?(ElevatorRequest)

    @elevator_id         == other.instance_variable_get(:@elevator_id) &&
      @floor             == other.instance_variable_get(:@floor) &&
      @is_internal_request == other.instance_variable_get(:@is_internal_request) &&
      @request_direction == other.instance_variable_get(:@request_direction)
  end

  def eql?(other)
    self == other
  end

  def hash
    [@elevator_id, @floor, @is_internal_request, @request_direction].hash
  end
end
