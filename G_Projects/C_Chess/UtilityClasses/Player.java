package G_Projects.C_Chess.UtilityClasses;

// BRAINSTORM: Why not use PlayerStrategy like we did in TicTacToe?

public class Player {
    private String name;
    private boolean isWhiteSide;

    public Player(String name, boolean isWhiteSide){
        this.name = name;
        this.isWhiteSide = isWhiteSide;
    }

    public String getName(){
        return name;
    }
    public boolean isWhiteSide(){
        return isWhiteSide;
    }
}
