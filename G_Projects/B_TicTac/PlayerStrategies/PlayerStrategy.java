package G_Projects.B_TicTac.PlayerStrategies;

import G_Projects.B_TicTac.Utility.Board;
import G_Projects.B_TicTac.Utility.Position;

/*
 * ═══════════════════════════════════════════════════════════════════════════════════
 *                           🎯 STRATEGY PATTERN: PLAYER MOVE STRATEGIES
 * ═══════════════════════════════════════════════════════════════════════════════════
 * 
 * 📋 What is the Strategy Pattern?
 *    • Defines a family of algorithms or strategies
 *    • Makes these algorithms interchangeable at runtime
 *    • Encapsulates each algorithm in separate classes
 * 
 * 🎮 How it works in Player Move Context:
 *    • PlayerStrategy interface defines: makeMove(Board board)
 *    • All concrete strategies must implement this method
 *    • Examples: HumanPlayerStrategy, AIPlayerStrategy, RandomPlayerStrategy
 * 
 * ✨ Key Benefits:
 *    • Different player strategies can be used interchangeably
 *    • No need to modify client code when adding new strategies
 *    • Clean separation of concerns
 *    • Easy to test individual strategies
 * 
 * 🔧 Implementation Flow:
 *    1. Define PlayerStrategy interface
 *    2. Create concrete strategy classes
 *    3. Client uses strategies without knowing implementation details
 *    4. Strategies can be swapped dynamically
 * ═══════════════════════════════════════════════════════════════════════════════════
 */

public interface PlayerStrategy {
    Position makeMove(Board board);
}