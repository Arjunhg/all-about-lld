package G_Projects.B_TicTac.Utility;

import G_Projects.B_TicTac.CentralEnum.Symbol;
import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;

/*
 * Board Representation:
 * 
 * The Board class serves as the core component for managing the Tic-Tac-Toe game state:
 * 
 * • Game Board Management:
 *   - Represents the 3x3 game grid
 *   - Initializes all positions with Symbol.EMPTY
 *   - Maintains current state of the board
 * 
 * • Move Operations:
 *   - Validates player moves before execution
 *   - Places symbols (X or O) on the board
 *   - Prevents invalid moves (occupied positions, out of bounds)
 * 
 * • Game State Analysis:
 *   - Checks for winning combinations
 *   - Detects draw conditions
 *   - Determines if game is still in progress
 * 
 * • Interactive Features:
 *   - Provides visual representation of current board state
 *   - Supports real-time game updates
 *   - Enables seamless player interaction
*/

public class Board {
    private final int rows;
    private final int cols;
    private Symbol[][] grid;

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Symbol[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    // Check if position is within bounds
    public boolean isValidMove(Position pos){
        if(pos==null) return false;
        return pos.row >= 0 && pos.col >= 0 && pos.row < rows && pos.col < cols && grid[pos.row][pos.col] == Symbol.EMPTY;
    }

    // Player can make the move now
    public void makeMove(Position pos, Symbol symbol){
        if(!isValidMove(pos)){
            throw new IllegalArgumentException("Invalid move: position out of bound or already occupied");
        }
        grid[pos.row][pos.col] = symbol;
    }

    // Check the current state of the board/game
    public void checkGameState(GameContext context, Player currPlayer){
        for(int i=0; i<rows; i++){
            if(grid[i][0] != Symbol.EMPTY && isWinningLine(grid[i])){
                context.next(currPlayer, true);
                return;
            }
        }

        for(int i=0; i<cols; i++){
            Symbol[] column = new Symbol[rows];
            for(int j=0; j<rows; j++){
                column[j] = grid[j][i];
            }
            if(column[0]!=Symbol.EMPTY && isWinningLine(column)){
                context.next(currPlayer, true);
                return;
            }
        }

        // Check diagonals and reverse diagnals
        Symbol[] diag1 = new Symbol[Math.min(rows, cols)];
        Symbol[] diag2 = new Symbol[Math.min(rows, cols)];
        for(int i=0; i<Math.min(rows, cols); i++){
            diag1[i] = grid[i][i];
            diag2[i] = grid[i][cols - 1 - i];
        }
        if(diag1[0]!=Symbol.EMPTY && isWinningLine(diag1)){
            context.next(currPlayer, true);
            return;
        }
        if(diag2[0]!=Symbol.EMPTY && isWinningLine(diag2)){
            context.next(currPlayer, true);
            return;
        }

        // Handling draw condition
        boolean isDraw = true;
        outer:
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j]==Symbol.EMPTY){
                    isDraw = false;
                    break outer;
                }
            }
        }
        if(isDraw){
            context.next(currPlayer, false);
        }
    }
    private boolean isWinningLine(Symbol[] line){
        Symbol first = line[0];
        for(Symbol s : line){
            if(s != first){
                return false;
            }
        }
        return true;
    }

    public void displayBoard(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                Symbol symbol = grid[i][j];

                // print cell on same line
                switch(symbol){
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                        System.out.print(" . ");
                        break;
                    default:
                        System.out.print(" . "); // Should never reach here
                }

                if(j<cols-1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i<rows-1){
                // build separator dynamically for any number of columns
                StringBuilder sep = new StringBuilder();
                for(int c=0; c<cols; c++){
                    sep.append("---");
                    if(c<cols-1) sep.append("+");
                }
                System.out.println(sep.toString());
            }
        }
        System.out.println();
    }
}
