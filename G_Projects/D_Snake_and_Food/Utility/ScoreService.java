package G_Projects.D_Snake_and_Food.Utility;

import G_Projects.D_Snake_and_Food.FoodItemFactory.FoodItem;

/*
 * ScoreService
 *
 * Responsibility:
 * - Accumulate points awarded by consumed FoodItem instances.
 * - Keeps scoring logic separate from movement/board logic (Single Responsibility).
 */
public class ScoreService {
    private int score;

    public void add(FoodItem item){
        if(item != null){
            score += item.getPoints();
        }
    }

    public int getScore(){
        return score;
    }

    public void reset(){
        score = 0;
    }
}
