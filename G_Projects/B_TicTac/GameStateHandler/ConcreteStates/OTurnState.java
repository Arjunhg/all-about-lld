package G_Projects.B_TicTac.GameStateHandler.ConcreteStates;

import G_Projects.B_TicTac.CentralEnum.Symbol;
import G_Projects.B_TicTac.GameStateHandler.GameState;
import G_Projects.B_TicTac.GameStateHandler.Context.GameContext;
import G_Projects.B_TicTac.Utility.Player;

public class OTurnState implements GameState{
    @Override
    public void next(GameContext context, Player player, boolean hasWon){
        if(hasWon){
            context.setState(player.getSymbol() == Symbol.O ? new OWonState() : new XWonState());
        }else{
            context.setState(new XTurnState());
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
