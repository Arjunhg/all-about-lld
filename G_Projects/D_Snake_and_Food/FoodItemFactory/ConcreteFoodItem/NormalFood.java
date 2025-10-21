package G_Projects.D_Snake_and_Food.FoodItemFactory.ConcreteFoodItem;

import G_Projects.D_Snake_and_Food.FoodItemFactory.FoodItem;

public class NormalFood extends FoodItem {
    public  NormalFood(int row, int col){
        super(row, col);
        this.points = 1; // Normal food gives 1 points
    }
}
