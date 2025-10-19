package G_Projects.B_TicTac.GameStateHandler.ConcreteStates;

import G_Projects.B_TicTac.GameStateHandler.GameState;
import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;
import G_Projects.B_TicTac.Utility.Player;

public class XWonState implements GameState{
    @Override
    public void next(GameContext context, Player player, boolean hasWon){
        // Game is over, no further state transitions
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public String getResultMessage() {
        return "Player X has won the game!";
    }
}
