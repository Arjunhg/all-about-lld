package G_Projects.D_Snake_and_Food.Utility;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
 * TextBoardRenderer
 *
 * Responsibility:
 * - Print a simple ASCII view of the game each tick.
 * - Shows snake body (S), snake head (H), and current food (F).
 *
 * Why a separate class?
 * - Single Responsibility: presentation concerns are isolated from game logic.
 * - Easy to swap for GUI later without touching the controller.
 */
public class TextBoardRenderer {
    public void render(GameBoard board, Deque<Pair> snakeBody, int[] currentFood){
        int h = board.getHeight();
        int w = board.getWidth();

        Set<Pair> bodySet = new HashSet<>(snakeBody);
        Pair head = snakeBody.peekFirst();

        for(int r=0; r<h; r++){
            StringBuilder row = new StringBuilder();
            for(int c=0; c<w; c++){
                Pair p = new Pair(r, c);
                if(head != null && head.equals(p)){
                    row.append('H');
                }else if(bodySet.contains(p)){
                    row.append('S');
                }else if(currentFood != null && currentFood[0]==r && currentFood[1]==c){
                    row.append('F');
                }else{
                    row.append('.');
                }
                if(c < w-1) row.append(' ');
            }
            System.out.println(row.toString());
        }
        System.out.println();
    }
}
