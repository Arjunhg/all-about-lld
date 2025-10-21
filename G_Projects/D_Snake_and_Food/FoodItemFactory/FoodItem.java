package G_Projects.D_Snake_and_Food.FoodItemFactory;

// FoodItemFactory follows Factory Design Pattern

/*
 * ○ FoodItem Abstract Class:
 * 
 * • Represents a food item that can be placed on the game board
 * • Defines common properties shared by all food types:
 *   - Position coordinates (row, column)
 *   - Points awarded when the snake consumes the food
 * • Serves as a base template for different food item implementations
 * • Ensures consistent behavior across all food varieties in the game
 */

public abstract class FoodItem {
    protected int row, col;
    protected int points;

    public FoodItem(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getPoints(){
        return points;
    }
}
