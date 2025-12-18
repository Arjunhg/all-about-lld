package G_Projects.C_Chess.MovementStrategyPattern;


import G_Projects.C_Chess.UtilityClasses.Board;
import G_Projects.C_Chess.UtilityClasses.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
