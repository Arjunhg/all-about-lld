package G_Projects.B_TicTac;

import G_Projects.B_TicTac.Controller.GameController.TicTacToeGame;
import G_Projects.B_TicTac.PlayerStrategies.PlayerStrategy;
import G_Projects.B_TicTac.PlayerStrategies.ConcreteStrategy.HumanPlayerStrategy;

public class Main {
    public static void main(String[] args) {
        
        PlayerStrategy playerXStrategy = new HumanPlayerStrategy("Player X");
        PlayerStrategy playerYStrategy = new HumanPlayerStrategy("Player O");
        TicTacToeGame game = new TicTacToeGame(playerXStrategy, playerYStrategy, 3, 3);
        game.play();
    }
}
