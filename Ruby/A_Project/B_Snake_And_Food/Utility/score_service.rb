# frozen_string_literal: true

class ScoreService
  def initialize
    @score = 0
  end

  def add(item)
    @score += item.get_points if !item.nil?
  end

  def get_score
    @score
  end

  def reset
    @score = 0
  end
end
