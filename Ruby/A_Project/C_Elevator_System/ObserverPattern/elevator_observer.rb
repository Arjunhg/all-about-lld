# Abstract observer interface (Observer Pattern)
class ElevatorObserver
  def on_elevator_state_change(elevator, state)
    raise NotImplementedError, "#{self.class}#on_elevator_state_change must be implemented"
  end

  def on_elevator_floor_change(elevator, floor)
    raise NotImplementedError, "#{self.class}#on_elevator_floor_change must be implemented"
  end
end
