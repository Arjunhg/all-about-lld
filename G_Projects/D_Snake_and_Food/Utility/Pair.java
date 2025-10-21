package G_Projects.D_Snake_and_Food.Utility;

/*
 - Represents a coordinate on the game board with row and column values.
 - Use it for positions of the snake and food to keep 2D coordinates clear and reusable.
 - Try: new Pair(row, col)
*/

public class Pair {
    private final int row;
    private final int col;

    public Pair(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        // For more details about getClass() look: getClass.md
        Pair pair = (Pair) o;
        return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode(){
        int result = Integer.hashCode(row);
        // 31 is an odd prime number — primes help reduce patterns and collisions
        // Efficient: 31 * i can be optimized by the compiler to (i << 5) - i (i.e., 32 * i - i)
        result = 31 * result + Integer.hashCode(col);
        return result;
    }

    @Override
    public String toString(){
        return "(" + row + "," + col + ")";
    }
}
