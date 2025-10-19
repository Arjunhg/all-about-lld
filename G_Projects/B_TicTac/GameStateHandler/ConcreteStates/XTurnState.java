package G_Projects.B_TicTac.GameStateHandler.ConcreteStates;

import G_Projects.B_TicTac.CentralEnum.Symbol;
import G_Projects.B_TicTac.GameStateHandler.GameState;
import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;
import G_Projects.B_TicTac.Utility.Player;

public class XTurnState implements GameState {
    @Override
    public void next(GameContext context, Player player, boolean hasWon){
        // After player has won we need a way to transition to the appropriate winning state and that's why context is needed here
        if(hasWon){
            context.setState(player.getSymbol() == Symbol.X ? new XWonState() : new OWonState());
        }else{
            context.setState(new OTurnState());
        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String getResultMessage() {
        return "Game is still in progress.";
    }
}
