package G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces;

import G_Projects.C_Chess.MovementStrategyPattern.ConcreteMovementStrategies.BishopMovementStrategy;
import G_Projects.C_Chess.PieceFactoryPackage.Piece;
import G_Projects.C_Chess.UtilityClasses.Board;
import G_Projects.C_Chess.UtilityClasses.Cell;

public class Bishop extends Piece{
    public Bishop(boolean isWhite){
        super(isWhite, new BishopMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
