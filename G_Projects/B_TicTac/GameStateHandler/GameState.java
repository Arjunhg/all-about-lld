package G_Projects.B_TicTac.GameStateHandler;

import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;
import G_Projects.B_TicTac.Utility.Player;

/*
 * State Pattern: Game State Management
 * 
 * What is the State Pattern?
 * • A behavioral design pattern that allows objects to change behavior based on internal state
 * • Enables clean state transitions without complex conditional logic
 * 
 * How does it work in our game?
 * • GameState interface defines different states a Tic-Tac-Toe game can be in:
 *   - IN_PROGRESS: Game is still being played
 *   - X_WON: Player X has won the game
 *   - O_WON: Player O has won the game
 *   - DRAW: Game ended with no winner
 * 
 * Key Benefits:
 * • Each state knows when the game is over (isGameOver() method)
 * • State transitions are handled cleanly via next() method
 * • Easy to add new game states without modifying existing code
 * • Separates state-specific logic from the main game context
 */

public interface GameState {
    void next(GameContext context, Player player, boolean hasWon);
    boolean isGameOver();
    String getResultMessage();
}
