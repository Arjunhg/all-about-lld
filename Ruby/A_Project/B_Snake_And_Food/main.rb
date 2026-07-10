# frozen_string_literal: true

require_relative "Controller/snake_game"

def convert_input(user_input)
  case user_input
  when "W" then "U"
  when "S" then "D"
  when "A" then "L"
  when "D" then "R"
  else ""
  end
end

def main
  width = 10
  height = 10

  food_positions = [
    [5, 5],
    [9, 8],
    [3, 7],
    [8, 1],
    [2, 3]
  ]

  game = SnakeGame.new(width, height, food_positions)

  puts "===== SNAKE GAME ====="
  puts "Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)"
  puts "Eat food to grow your snake and increase your score."
  puts "Do not hit the walls or bite yourself!"
  puts "======================="

  loop do
    print "Enter move (W/A/S/D) or Q to quit: "
    $stdout.flush

    input = STDIN.gets
    if input.nil?
      puts "\nFinal Score: #{game.get_score}"
      break
    end

    user_input = input.strip.upcase
    if user_input == "Q"
      puts "Final Score: #{game.get_score}"
      break
    end

    direction = convert_input(user_input)
    if direction.empty?
      puts "Invalid input! Please use W/A/S/D to move or Q to quit."
      next
    end

    score = game.move(direction)
    if score == -1
      puts "Game Over! Final Score: #{game.get_score}"
      break
    else
      puts "Current Score: #{game.get_score}"
    end
  end

  puts "Thanks for playing!"
end

main if __FILE__ == $PROGRAM_NAME
