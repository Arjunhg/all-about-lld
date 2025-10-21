package G_Projects.D_Snake_and_Food.Utility;

public class Food {
    private int[][] foodPositions;
    private int currrentFoodIndex;

    public Food(int[][] foodPositions){
        this.foodPositions = foodPositions;
        this.currrentFoodIndex = 0;
    }

    public boolean hasMore(){
        return currrentFoodIndex < foodPositions.length;
    }

    public int[] current(){
        if(!hasMore()) return null;
        return foodPositions[currrentFoodIndex];
    }

    public void consume(){
        if(hasMore()) currrentFoodIndex++;
    }
}
