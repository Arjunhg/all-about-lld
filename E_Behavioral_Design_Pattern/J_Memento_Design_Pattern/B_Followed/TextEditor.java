package E_Behavioral_Design_Pattern.J_Memento_Design_Pattern.B_Followed;

// This class will have the current state (the text) and methods to save and restore its state.

public class TextEditor {
    private String text;

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    // Create Snapshot
    public Memento save(){
        return new Memento(text);
    }

    // Restrore from snapshot
    public void restore(Memento memento){
        this.text = memento.getText();
    }

    // === RESTORE METHOD: WHAT NOT TO DO ===
    // 
    // ❌ INCORRECT APPROACH:
    // public void restore(){
    //     this.text = new Memento(text).getText();
    // }
    //
    // 🚨 WHY THIS APPROACH IS PROBLEMATIC:
    //
    // 1. 🔄 CIRCULAR LOGIC ISSUE:
    //    • Creates a new memento from current (potentially corrupted) state
    //    • Instead of using a previously saved memento
    //
    // 2. ❌ NO ACTUAL RESTORATION:
    //    • Just reassigns current text to itself
    //    • No real state change occurs
    //
    // 3. 🎯 DEFEATS THE PURPOSE:
    //    • Memento pattern = restore from PREVIOUSLY SAVED state
    //    • Not creating new mementos during restoration
    //
    // ✅ CORRECT APPROACH SHOULD:
    //    • Accept a memento parameter: restore(Memento memento)
    //    • Extract state from the memento: this.text = memento.getText()
    //    • Actually restore to a previous state
}
