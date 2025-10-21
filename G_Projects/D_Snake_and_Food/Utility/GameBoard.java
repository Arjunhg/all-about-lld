package G_Projects.D_Snake_and_Food.Utility;

public class GameBoard {
    // Game board dimensions (width x height)
    // Current snake position and body segments
    // Food position and spawn logic
    // Obstacles/walls (if any)
    // Game state (running, paused, game over)

    private static GameBoard instance;
    private int width;
    private int height;

    private GameBoard(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static GameBoard getInstance(int widht, int height){
        if(instance == null){
            instance = new GameBoard(widht, height);
        }
        return instance;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
