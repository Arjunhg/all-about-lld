# frozen_string_literal: true

class MovementStrategy
  def get_next_position(_current_head, _direction)
    raise NotImplementedError, "#{self.class} must implement #get_next_position"
  end
end
