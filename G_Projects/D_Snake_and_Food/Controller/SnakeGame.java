package G_Projects.D_Snake_and_Food.Controller;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import G_Projects.D_Snake_and_Food.MovementStrategyModule.MovementStrategy;
import G_Projects.D_Snake_and_Food.MovementStrategyModule.ConcreteMovementStrategies.HumanMovementStrategy;
import G_Projects.D_Snake_and_Food.Utility.GameBoard;
import G_Projects.D_Snake_and_Food.Utility.Pair;
import G_Projects.D_Snake_and_Food.Utility.Food;
import G_Projects.D_Snake_and_Food.Utility.Snake;
import G_Projects.D_Snake_and_Food.Utility.ScoreService;
import G_Projects.D_Snake_and_Food.Utility.TextBoardRenderer;
import G_Projects.D_Snake_and_Food.FoodItemFactory.FoodSpawner;
import G_Projects.D_Snake_and_Food.FoodItemFactory.FoodItem;

public class SnakeGame {
    private GameBoard gameBoard;
    private Snake snake;
    private Map<Pair, Boolean> snakeMap; // for collision detection
    private Food food; // legacy array-backed food list (kept for compatibility)
    private FoodSpawner foodSpawner; // Factory-backed, scheduled food emitter
    private ScoreService scoreService; // accumulates points per food item
    private TextBoardRenderer renderer; // simple ASCII view
    private MovementStrategy movementStrategy;

    public SnakeGame(int width, int height, int[][] food){
        this.gameBoard = GameBoard.getInstance(width, height);
        this.food = new Food(food); // still used to expose current food position to renderer
        this.foodSpawner = new FoodSpawner(food, 3); // Every 3rd item is bonus (Factory Pattern)

    // snake initialization: start at board center to reduce immediate wall collisions
        int startRow = height / 2;
        int startCol = width / 2;
        this.snake = new Snake(new Pair(startRow, startCol));
        this.snakeMap = new HashMap<>();
        this.snakeMap.put(this.snake.head(), true);

    // services / presentation
        this.scoreService = new ScoreService();
        this.renderer = new TextBoardRenderer();

        // default movement strategy (Strategy Pattern)
        this.movementStrategy = new HumanMovementStrategy();

        // Render initial state so the player sees the starting position
        this.renderer.render(this.gameBoard, this.snake.body(), this.food.current());
    }

    public void setMovementStrategy(MovementStrategy strategy){
        this.movementStrategy = strategy;
    }

    // return score after move or -1 if game over
    public int move(String direction){
        // get head
        Pair currHead = this.snake.head();

        // get next position from movement strategy
        Pair newHead = this.movementStrategy.getNextPosition(currHead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();

        // Check for valid move
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= this.gameBoard.getHeight() || newHeadCol < 0 || newHeadCol >= this.gameBoard.getWidth();

        // Check for self-collision using tail
        Pair currTail = this.snake.tail();

        // It's okay if the new head position is where the tail currently is, because the tail is about to move away (unless the snake is eating food).
        boolean selfCollision = this.snakeMap.containsKey(newHead) && !(newHead.getRow() == currTail.getRow() && newHead.getCol() == currTail.getCol());
        // snakeMap.containsKey(newHead) — "Is the new head position already occupied by the snake?"
        // !(...) — "But wait! If the new head position is exactly at the current tail, that's fine because the tail will move away — unless we're eating food."

        if(crossesBoundary || selfCollision) return -1;

        // This line checks if the snake has just eaten food by moving its head into a position where food is currently located.
        // Determine if we ate the current scheduled food item
        FoodItem currentItem = this.foodSpawner.currentItem();
        boolean ateFood = false;
        if(currentItem != null){
            ateFood = (currentItem.getRow() == newHeadRow) && (currentItem.getCol() == newHeadCol);
        }
        /*“Is the snake's new head position exactly the same as the current food's position? And is there still food left?”
         *  Condition 1: this.foodIndex < this.food.length

                Ensures we're not past the last food item.
                Prevents ArrayIndexOutOfBoundsException.

            Condition 2: this.food[this.foodIndex][0] == newHeadRow

                Checks if the new head row matches the food’s row.

            Condition 3: this.food[this.foodIndex][1] == newHeadCol

                Checks if the new head column matches the food’s column.
         */
        
        if(ateFood){
            // award points based on FoodItem (Normal vs Bonus)
            this.scoreService.add(currentItem);
            // advance spawner schedule and legacy list pointer
            // this.foodSpawner.consume();
            this.food.consume(); //update foodSpawner for this instead?
            // grow snake
            this.snake.growTo(newHead);
        }else{
            // move forward without growing
            this.snake.moveTo(newHead);
            this.snakeMap.remove(currTail);
        }

        this.snakeMap.put(newHead, true);

        // render tick (presentation separated from logic)
        this.renderer.render(this.gameBoard, this.snake.body(), this.food.current());

        // score: using service (points), but maintain compatibility by returning length-1 if no FoodItem consumed
        return this.scoreService.getScore();
    }

    public Deque<Pair> getSnake(){
        return snake.body();
    }

    // Expose accumulated score from ScoreService so UI (Main) can display it
    public int getScore(){
        return this.scoreService.getScore();
    }
}
