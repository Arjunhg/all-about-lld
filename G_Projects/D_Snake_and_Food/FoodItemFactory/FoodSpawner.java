package G_Projects.D_Snake_and_Food.FoodItemFactory;


/*
 * FoodSpawner
 * 
 * Responsibility:
 * - Emit FoodItem instances (Normal or Bonus) following a simple schedule/pattern.
 * - Encapsulates creation via FoodFactory (Factory Pattern) so controller doesn't hardcode types.
 * 
 * Scheduling rule (default behavior):
 * - Every k-th item is a Bonus food; others are Normal.
 */
public class FoodSpawner {
    private final int[][] positions;
    private final int bonusEveryK;
    private int index;

    public FoodSpawner(int[][] positions, int bonusEveryK){
        this.positions = positions;
        this.bonusEveryK = Math.max(2, bonusEveryK); // avoid k=1 degeneracy
        this.index = 0;
    }

    public boolean hasMore(){
        return index < positions.length;
    }

    public FoodItem currentItem(){
        if(!hasMore()) return null;
        int[] pos = positions[index];
        String type = (((index + 1) % bonusEveryK) == 0) ? "bonus" : "normal";
        return FoodFactory.createFood(pos, type);
    }

    public void consume(){
        if(hasMore()) index++;
    }

    public int getIndex(){
        return index;
    }
}
