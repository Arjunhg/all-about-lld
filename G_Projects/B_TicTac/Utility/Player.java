package G_Projects.B_TicTac.Utility;

import G_Projects.B_TicTac.CentralEnum.Symbol;
import G_Projects.B_TicTac.PlayerStrategies.PlayerStrategy;

/**
 * Player Class - Represents a game participant in Tic-Tac-Toe
 * 
 * Key Features:
 * • Stores player's unique symbol (X or O)
 * • Manages player's move strategy
 * • Handles turn-based gameplay mechanics
 * 
 * Responsibilities:
 * • Maintain player identity through symbol
 * • Execute moves using assigned strategy
 * • Provide player information to game engine
 * 
 * Usage:
 * • Create players with specific symbols and strategies
 * • Retrieve player symbol for board updates
 * • Access strategy for move execution
 */

public class Player {
    private Symbol symbol;
    private PlayerStrategy playerStrategy;

    public Player(Symbol symbol, PlayerStrategy playerStrategy){
        this.symbol = symbol;
        this.playerStrategy = playerStrategy;
    }

    public Symbol getSymbol(){
        return this.symbol;
    }
    public PlayerStrategy getPlayerStrategy(){
        return this.playerStrategy;
    }
}
