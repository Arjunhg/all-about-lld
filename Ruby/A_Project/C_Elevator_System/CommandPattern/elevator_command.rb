# Abstract base class for elevator commands (Command Pattern)
class ElevatorCommand
  def execute
    raise NotImplementedError, "#{self.class}#execute must be implemented"
  end
end
