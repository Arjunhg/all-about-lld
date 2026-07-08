# Used Strategy Design Pattern

require_relative '../player_strategy'
require_relative '../../Utility/position'

module PlayerStrategies
  module ConcreteStrategy
    class HumanPlayerStrategy
      include PlayerStrategy

      def initialize(player_name)
        @player_name = player_name
      end

      def make_move(board)
        loop do
          print "#{@player_name}, enter your move (row [0-2] and column [0-2]): "

          begin
            input = gets.chomp.split
            raise ArgumentError unless input.length == 2

            row = Integer(input[0])
            col = Integer(input[1])
            move = Utility::Position.new(row, col)

            return move if board.valid_move?(move)

            puts "This move is not valid. Try again."
          rescue ArgumentError
            puts "Invalid input. Please enter numbers for row and column."
          end
        end
      end
    end
  end
end
