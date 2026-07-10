# frozen_string_literal: true

class GameBoard
  class << self
    def get_instance(width, height)
      if @instance.nil?
        @instance = new(width, height)
      elsif @instance.width != width || @instance.height != height
        raise ArgumentError, "GameBoard already initialized as #{@instance.width}x#{@instance.height}, got #{width}x#{height}"
      end

      @instance
    end
  end

  attr_reader :width, :height

  def initialize(width, height)
    raise ArgumentError, "width and height must be positive" if width <= 0 || height <= 0

    @width = width
    @height = height
  end

  def get_width
    @width
  end

  def get_height
    @height
  end
end
