package G_Projects.D_Snake_and_Food.MovementStrategyModule;

import G_Projects.D_Snake_and_Food.Utility.Pair;

public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}
