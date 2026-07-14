# frozen_string_literal: true

require_relative "pair"

class Snake
  def initialize(start = nil)
    @body_segments = []
    if start.nil?
      @body_segments.unshift(Pair.new(0, 0))
    else
      @body_segments.unshift(start)
    end
  end

  def head
    @body_segments[0]
  end

  def tail
    @body_segments[-1]
  end

  def move_to(new_head)
    @body_segments.unshift(new_head)
    @body_segments.pop
  end

  def grow_to(new_head)
    @body_segments.unshift(new_head)
  end

  def length
    @body_segments.length
  end

  def body
    @body_segments.dup
  end
end
