package G_Projects.B_TicTac.Utility;

/*
 * POSITION CLASS OVERVIEW
 * 
 * • Purpose: Represents row and column coordinates on the game board
 * • Class Name: Position
 * 
 * Key Features:
 * ✓ Encapsulates board position data
 * ✓ Used to represent move locations
 * ✓ Supports equality checks
 * ✓ Provides readable formatting
 * 
 * Usage: Essential for tracking player moves and board state management
 */

public class Position {
    public int row;
    public int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString(){
        return "(" + row + ", " + col + ")";
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Position)) return false;
        Position other = (Position)obj;
        return this.row==other.row && this.col==other.col;
    }

    @Override
    public int hashCode(){
        return 31*row+col;
    }
}
