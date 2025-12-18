package G_Projects.C_Chess.UtilityClasses;

import G_Projects.C_Chess.PieceFactoryPackage.Piece;

/*
 * Cell Class - Building Block of Our Chess Board
 * ============================================
 * 
 * Key Points:
 * • Foundation Component: The Cell class serves as the fundamental building block for our chess board
 * • Individual Representation: Each square on the chess board is represented by one Cell instance
 * • Core Attributes: Every cell encapsulates essential information including:
 *   - Position coordinates (row and column)
 *   - Current piece occupying the cell (if any)
 * • Board Relationship: Multiple Cell instances come together to form the complete Board structure
 * • Design Priority: Understanding the Cell class is crucial before implementing the Board class
 */

public class Cell {
    private int row, col;
    private String label;
    private Piece piece;

    public Cell(int row, int col, Piece piece){
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }
}
