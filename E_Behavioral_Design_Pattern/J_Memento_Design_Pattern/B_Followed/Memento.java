package E_Behavioral_Design_Pattern.J_Memento_Design_Pattern.B_Followed;

// Storing the state / snapshot of the TextEditor

public class Memento {

    private final String text;

    public Memento(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
