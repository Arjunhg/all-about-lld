# Player Class - Represents a game participant in Tic-Tac-Toe
#
# Key Features:
# • Stores player's unique symbol (X or O)
# • Manages player's move strategy
# • Handles turn-based gameplay mechanics
#
# Responsibilities:
# • Maintain player identity through symbol
# • Execute moves using assigned strategy
# • Provide player information to game engine
#
# Usage:
# • Create players with specific symbols and strategies
# • Retrieve player symbol for board updates
# • Access strategy for move execution

module Utility
  class Player
    attr_reader :symbol, :player_strategy

    def initialize(symbol, player_strategy)
      @symbol          = symbol
      @player_strategy = player_strategy
    end
  end
end
