package G_Projects.B_TicTac.GameStateHandler.ConcreteStates;

import G_Projects.B_TicTac.GameStateHandler.GameState;
import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;
import G_Projects.B_TicTac.Utility.Player;

public class OWonState implements GameState{
    @Override
    public void next(GameContext context, Player player, boolean hasWon){
        // Game is already over, no further state transitions
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public String getResultMessage() {
        return "Player O has won the game!";
    }
}
