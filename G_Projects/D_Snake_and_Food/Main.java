package G_Projects.D_Snake_and_Food;

import java.util.Scanner;

import G_Projects.D_Snake_and_Food.Controller.SnakeGame;

public class Main {
    public static void main(String[] args){

        // Define game cofig which can be initiated by user too
        int width = 10;
        int height = 10;

        int[][] foodPositions = {
            {5, 5}, // Initial food
            {9, 8}, // Second food
            {3, 7}, // Third food
            {8, 1}, // Fourth food
            {2, 3} // Fifth food
        };

        SnakeGame game = new SnakeGame(width, height, foodPositions);

        // Display game instructions
        System.out.println("===== SNAKE GAME =====");
        System.out.println(
            "Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
        System.out.println("Eat food to grow your snake and increase your score.");
        System.out.println("Don't hit the walls or bite yourself!");
        System.out.println("=======================");

        Scanner sc = new Scanner(System.in);
        boolean gameRunning = true;
        int score = 0; // maintained by SnakeGame via ScoreService; still used for control flow

        while(gameRunning){
            // Rendering is handled by SnakeGame's TextBoardRenderer after each move.

            System.out.print("Enter move (W/A/S/D) or Q to quit: ");

            String input = sc.nextLine().toUpperCase();

            if(input.equals("Q")){
                System.out.println("Thanks for playing! Final Score: " + game.getScore());
                gameRunning = false;
                continue;
            }

            // convert input to direction and if no direction given, show invalid input
            String direction = convertInput(input);
            if(direction.isEmpty()){
                System.out.println("Invalid input! Please use W/A/S/D to move or Q to quit.");
                continue;
            }

            score = game.move(direction); // returns accumulated score from ScoreService

            if(score==-1){
                System.out.println("Game Over! Final Score: " + game.getScore());
                gameRunning = false;
            }else{
                System.out.println("Current Score: " + game.getScore());
            }
        }
        sc.close();
        System.out.println("Thanks for playing!");
    }

    private static String convertInput(String input) {
        switch (input) {
            case "W":
                return "U"; // Up
            case "S":
                return "D"; // Down
            case "A":
                return "L"; // Left
            case "D":
                return "R"; // Right
            default:
                return ""; // Invalid input
        }
    }

}
