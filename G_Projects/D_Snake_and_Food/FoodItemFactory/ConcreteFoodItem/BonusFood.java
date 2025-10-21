package G_Projects.D_Snake_and_Food.FoodItemFactory.ConcreteFoodItem;

import G_Projects.D_Snake_and_Food.FoodItemFactory.FoodItem;

public class BonusFood extends FoodItem {
    public BonusFood(int row, int col){
        super(row, col);
        this.points = 5; // Bonus food gives 5 points
    }
}
