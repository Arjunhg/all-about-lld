package G_Projects.D_Snake_and_Food.FoodItemFactory;

import G_Projects.D_Snake_and_Food.FoodItemFactory.ConcreteFoodItem.BonusFood;
import G_Projects.D_Snake_and_Food.FoodItemFactory.ConcreteFoodItem.NormalFood;

public class FoodFactory {
    public static FoodItem createFood(int[] pos, String type){
        if(type.equals("bonus")){
            return new BonusFood(pos[0], pos[1]);
        }else{
            return new NormalFood(pos[0], pos[1]);
        }
    }
}
