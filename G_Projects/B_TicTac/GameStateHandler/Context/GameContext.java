package G_Projects.B_TicTac.GameStateHandler.Context;

import G_Projects.B_TicTac.GameStateHandler.GameState;
import G_Projects.B_TicTac.GameStateHandler.ConcreteStates.XTurnState;
import G_Projects.B_TicTac.Utility.Player;

public class GameContext {
    private GameState currentState;

    public GameContext(){
        currentState = new XTurnState(); // Start with X's turn
    }

    public void setState(GameState state){
        this.currentState = state;
    }
    public void next(Player player, boolean hasWon){
        currentState.next(this, player, hasWon);
    }
    public boolean isGameOver(){
        return currentState.isGameOver();
    }
    public GameState getCurrentState(){
        return currentState;
    }
}
