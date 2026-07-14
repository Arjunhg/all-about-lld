# Abstract strategy for determining the next floor stop (Strategy Pattern)
class SchedulingStrategy
  def get_next_stop(elevator)
    raise NotImplementedError, "#{self.class}#get_next_stop must be implemented"
  end
end
