package G_Projects.C_Chess.PieceFactoryPackage;

import G_Projects.C_Chess.MovementStrategyPattern.MovementStrategy;
import G_Projects.C_Chess.UtilityClasses.Board;
import G_Projects.C_Chess.UtilityClasses.Cell;

public abstract class Piece {
    private boolean isWhitePiece;
    private boolean killed = false;

    private MovementStrategy movementStrategy;

    public Piece(boolean isWhitePiece, MovementStrategy movementStrategy){
        this.isWhitePiece = isWhitePiece;
        this.movementStrategy = movementStrategy;
    }

    public boolean isWhitePiece(){
        return isWhitePiece;
    }

    public boolean isKilled(){
        return killed;
    }

    public void setKilled(boolean killed){
        this.killed = killed;
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell){
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
