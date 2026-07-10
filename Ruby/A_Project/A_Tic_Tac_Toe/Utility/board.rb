# Board Representation:
#
# The Board class serves as the core component for managing the Tic-Tac-Toe game state:
#
# • Game Board Management:
#   - Represents the NxN game grid
#   - Initializes all positions with Symbol::EMPTY
#   - Maintains current state of the board
#
# • Move Operations:
#   - Validates player moves before execution
#   - Places symbols (X or O) on the board
#   - Prevents invalid moves (occupied positions, out of bounds)
#
# • Game State Analysis:
#   - Checks for winning combinations
#   - Detects draw conditions
#   - Determines if game is still in progress
#
# • Interactive Features:
#   - Provides visual representation of current board state
#   - Supports real-time game updates
#   - Enables seamless player interaction

require_relative '../CentralEnum/symbol'

module Utility
  class Board
    def initialize(rows, cols)
      @rows = rows
      @cols = cols
      @grid = Array.new(rows) { Array.new(cols, CentralEnum::Symbol::EMPTY) }
    end

    # Check if position is within bounds and not already occupied
    def valid_move?(pos)
      return false if pos.nil?
      pos.row >= 0 && pos.col >= 0 &&
        pos.row < @rows && pos.col < @cols &&
        @grid[pos.row][pos.col] == CentralEnum::Symbol::EMPTY
    end

    # Player can make the move now
    def make_move(pos, symbol)
      raise ArgumentError, "Invalid move: position out of bounds or already occupied" unless valid_move?(pos)
      @grid[pos.row][pos.col] = symbol
    end

    # Check the current state of the board/game
    def check_game_state(context, curr_player)
      # Check rows
      @rows.times do |i|
        row_line = @grid[i]
        if row_line[0] != CentralEnum::Symbol::EMPTY && winning_line?(row_line)
          context.next(curr_player, true)
          return
        end
      end

      # Check columns
      @cols.times do |i|
        column = Array.new(@rows) { |j| @grid[j][i] }
        if column[0] != CentralEnum::Symbol::EMPTY && winning_line?(column)
          context.next(curr_player, true)
          return
        end
      end

      # Check main diagonal
      diag_size = [@rows, @cols].min
      diag1 = Array.new(diag_size) { |i| @grid[i][i] }
      if diag1[0] != CentralEnum::Symbol::EMPTY && winning_line?(diag1)
        context.next(curr_player, true)
        return
      end

      # Check anti-diagonal
      diag2 = Array.new(diag_size) { |i| @grid[i][@cols - 1 - i] }
      if diag2[0] != CentralEnum::Symbol::EMPTY && winning_line?(diag2)
        context.next(curr_player, true)
        return
      end

      # Check draw condition
      is_draw = @grid.none? { |row| row.include?(CentralEnum::Symbol::EMPTY) }
      context.next(curr_player, false) if is_draw
    end

    def display_board
      @rows.times do |i|
        @cols.times do |j|
          symbol = @grid[i][j]
          case symbol
          when CentralEnum::Symbol::X     then print " X "
          when CentralEnum::Symbol::O     then print " O "
          when CentralEnum::Symbol::EMPTY then print " . "
          end
          print "|" if j < @cols - 1
        end
        puts
        if i < @rows - 1
          sep = Array.new(@cols, "---").join("+")
          puts sep
        end
      end
      puts
    end

    private

    def winning_line?(line)
      first = line[0]
      line.all? { |s| s == first }
    end
  end
end
