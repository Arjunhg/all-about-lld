package G_Projects.D_Snake_and_Food.Utility;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private final Deque<Pair> body; // snake body segments

    public Snake(){
        this.body = new LinkedList<>();
        // initialize snake at origin
        this.body.offerFirst(new Pair(0, 0));
    }

    // Overloaded constructor to start the snake at a specific position (e.g., board center)
    public Snake(Pair start){
        this.body = new LinkedList<>();
        this.body.offerFirst(start);
    }

    public Pair head(){
        return body.peekFirst();
    }

    public Pair tail(){
        return body.peekLast();
    }

    public void moveTo(Pair newHead){
        body.offerFirst(newHead);
        body.pollLast();
    }

    public void growTo(Pair newHead){
        // add head but keep tail (increase length)
        body.offerFirst(newHead);
    }

    public int length(){
        return body.size();
    }

    public Deque<Pair> body(){
        return body;
    }
}
