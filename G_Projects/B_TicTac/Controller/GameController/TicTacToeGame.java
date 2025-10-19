package G_Projects.B_TicTac.Controller.GameController;

import G_Projects.B_TicTac.CentralEnum.Symbol;
import G_Projects.B_TicTac.Controller.BoardGames;
import G_Projects.B_TicTac.GameStateHandler.GameState;
import G_Projects.B_TicTac.GameStateHandler.ConcreteStates.OWonState;
import G_Projects.B_TicTac.GameStateHandler.ConcreteStates.XWonState;
import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;
import G_Projects.B_TicTac.PlayerStrategies.PlayerStrategy;
import G_Projects.B_TicTac.Utility.Board;
import G_Projects.B_TicTac.Utility.Player;
import G_Projects.B_TicTac.Utility.Position;

/**
 * TicTacToeGame Class - Game Flow Controller
 * 
 * This class manages the complete game experience by handling:
 * 
 * • Game Board Management
 *   - Initializes and maintains the game board state
 *   - Tracks cell occupancy and game progress
 * 
 * • Player Management
 *   - Sets up both players (X and O) with their respective strategies
 *   - Manages player-specific game logic and moves
 * 
 * • Turn Management
 *   - Controls whose turn it is to play
 *   - Defaults to playerX at game start (configurable to playerO)
 *   - Alternates turns between players throughout the game
 * 
 * • Game Flow Control
 *   - Orchestrates the overall game experience
 *   - Coordinates between board, players, and turn management
 * 
 * Usage: Acts as the main controller for TicTacToe game logic
 */

/**
 * 🎮 TicTacToeGame Class - Interactive Game Flow Controller
 * 
 * ═══════════════════════════════════════════════════════════════
 * 🏗️  ARCHITECTURE PATTERN: Controller Pattern Implementation
 * ═══════════════════════════════════════════════════════════════
 * 
 * 🎯 Primary Responsibilities:
 * ┌─────────────────────────────────────────────────────────────┐
 * │ • Game Flow Management    - Orchestrates complete gameplay   │
 * │ • Board State Control     - Maintains game board integrity   │  
 * │ • Player Coordination     - Manages both X and O players     │
 * │ • Turn-based Logic       - Ensures smooth player alternation │
 * └─────────────────────────────────────────────────────────────┘
 * 
 * 🔧 Design Benefits & Features:
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 * 📦 Encapsulation Excellence:
 *    ✅ Game flow logic contained within TicTacToeGame class
 *    ✅ Clear separation of concerns achieved
 *    ✅ Modular and maintainable code structure
 * 
 * 🚀 Extensibility Features:
 *    ✅ Easy addition of new player strategies
 *    ✅ Simple game rule modifications
 *    ✅ Core game logic remains unaffected by changes
 * 
 * 🎛️  Flexible Configuration:
 *    ✅ Pluggable player strategies via Strategy Pattern
 *    ✅ Configurable board dimensions
 *    ✅ State management through GameContext
 * 
 * 💡 Usage: Deploy as the central controller for TicTacToe gameplay
 */

public class TicTacToeGame implements BoardGames {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameContext gameContext;

    public TicTacToeGame(PlayerStrategy xStrategy, PlayerStrategy oStrategy, int rows, int cols){
        board = new Board(rows, cols);
        playerX = new Player(Symbol.X, xStrategy);
        playerO = new Player(Symbol.O, oStrategy);
        currentPlayer = playerX; // X starts first by default
        gameContext = new GameContext();
    }

    @Override
    public void play(){
        // Continue until game is over
        do{
            // curr state of game
            board.displayBoard();

            // Current player makes a move
            Position move = currentPlayer.getPlayerStrategy().makeMove(board);
            board.makeMove(move, currentPlayer.getSymbol());

            // Check game state after the move
            board.checkGameState(gameContext, currentPlayer);
            switchPlayer();
        }while(!gameContext.isGameOver());
        announceResult();
    }

    // Switch to the other player alternatively
    private void switchPlayer(){
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    // Announce the final result of the game based on game state
    private void announceResult(){
       board.displayBoard();
       if(gameContext == null || gameContext.getCurrentState() == null) {
           System.out.println("Game result unavailable.");
           return;
       }
       System.out.println(gameContext.getCurrentState().getResultMessage()); //interface driven polymorphism
    }
}
