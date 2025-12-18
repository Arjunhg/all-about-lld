package G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces;

import G_Projects.C_Chess.MovementStrategyPattern.ConcreteMovementStrategies.KnightMovementStrategy;
import G_Projects.C_Chess.PieceFactoryPackage.Piece;
import G_Projects.C_Chess.UtilityClasses.Board;
import G_Projects.C_Chess.UtilityClasses.Cell;

public class Knight extends Piece {
    public Knight(boolean isWhite){
        super(isWhite, new KnightMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
