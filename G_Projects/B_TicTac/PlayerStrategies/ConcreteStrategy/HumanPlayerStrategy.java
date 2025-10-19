package G_Projects.B_TicTac.PlayerStrategies.ConcreteStrategy;

import G_Projects.B_TicTac.PlayerStrategies.PlayerStrategy;
import G_Projects.B_TicTac.Utility.Board;
import G_Projects.B_TicTac.Utility.Position;

import java.util.Scanner;

// Used Strategy Design Pattern

public class HumanPlayerStrategy implements PlayerStrategy{
    private Scanner scanner;
    private String playerName;

    public HumanPlayerStrategy(String playerName){
        this.playerName = playerName;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Position makeMove(Board board){
        while(true){
            System.out.printf(
                "%s, enter your move (row [0-2] and column [0-2]): ", playerName
            );

            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Position move = new Position(row, col);

                if(board.isValidMove(move)){
                    return move;
                }

                System.out.println("This move is not valid. Try again.");
            } catch (Exception e) {
                System.out.println(
                    "Invalid input. Please enter numbers for row and column."
                );
                scanner.nextLine(); // clear the invalid input
            }
        }
    }
}
