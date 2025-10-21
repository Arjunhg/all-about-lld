package G_Projects.D_Snake_and_Food.MovementStrategyModule.ConcreteMovementStrategies;

import G_Projects.D_Snake_and_Food.MovementStrategyModule.MovementStrategy;
import G_Projects.D_Snake_and_Food.Utility.Pair;

public class AIMovementStrategy implements MovementStrategy{
    @Override
    public Pair getNextPosition(Pair currentHead, String direction){
        // Pathfinding logic to determine next position
        return currentHead; // Placeholder return
    }
}
