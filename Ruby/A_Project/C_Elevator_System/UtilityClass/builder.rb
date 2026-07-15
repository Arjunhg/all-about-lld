require_relative 'elevator_controller'

# Builder / façade that represents the physical building
class Building
  def initialize(name, number_of_floors, number_of_elevators)
    @name               = name
    @number_of_floors   = number_of_floors
    @elevator_controller = ElevatorController.new(number_of_elevators, number_of_floors)
  end

  def get_name
    @name
  end

  def get_number_of_floors
    @number_of_floors
  end

  def get_elevator_controller
    @elevator_controller
  end
end
