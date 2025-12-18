package G_Projects.C_Chess.MovementStrategyPattern.ConcreteMovementStrategies;

import G_Projects.C_Chess.MovementStrategyPattern.MovementStrategy;
import G_Projects.C_Chess.UtilityClasses.Board;
import G_Projects.C_Chess.UtilityClasses.Cell;

public class BishopMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return true;
    }
}
