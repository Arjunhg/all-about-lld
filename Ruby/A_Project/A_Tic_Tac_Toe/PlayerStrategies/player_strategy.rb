# ═══════════════════════════════════════════════════════════════════════════════════
#                          STRATEGY PATTERN: PLAYER MOVE STRATEGIES
# ═══════════════════════════════════════════════════════════════════════════════════
#
# What is the Strategy Pattern?
#    • Defines a family of algorithms or strategies
#    • Makes these algorithms interchangeable at runtime
#    • Encapsulates each algorithm in separate classes
#
# How it works in Player Move Context:
#    • PlayerStrategy module defines: make_move(board)
#    • All concrete strategies must implement this method
#    • Examples: HumanPlayerStrategy, AIPlayerStrategy, RandomPlayerStrategy
#
# Key Benefits:
#    • Different player strategies can be used interchangeably
#    • No need to modify client code when adding new strategies
#    • Clean separation of concerns
#    • Easy to test individual strategies
#
# Implementation Flow:
#    1. Define PlayerStrategy module (interface equivalent)
#    2. Create concrete strategy classes
#    3. Client uses strategies without knowing implementation details
#    4. Strategies can be swapped dynamically
# ═══════════════════════════════════════════════════════════════════════════════════

module PlayerStrategies
  module PlayerStrategy
    # All concrete strategies must implement this method.
    # Raises NotImplementedError to enforce the contract (Ruby's interface pattern).
    def make_move(board)
      raise NotImplementedError, "#{self.class}#make_move is not implemented"
    end
  end
end
