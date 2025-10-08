package E_Behavioral_Design_Pattern.J_Memento_Design_Pattern.B_Followed;

import java.util.Stack;

// Caretaker: Manages the history of mementos (snapshots)

public class EditorHistory {
    
    private Stack<Memento> history = new Stack<>();

    public void push(Memento memento){
        history.push(memento);
    }

    public Memento pop(){
        if(!history.isEmpty()){
            return history.pop();
        }
        return null;
    }
}
