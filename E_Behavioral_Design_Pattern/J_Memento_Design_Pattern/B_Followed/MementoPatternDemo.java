package E_Behavioral_Design_Pattern.J_Memento_Design_Pattern.B_Followed;

public class MementoPatternDemo {
    public static void main(String[] args) {
        
        TextEditor textEditor = new TextEditor();
        EditorHistory history = new EditorHistory();

        textEditor.setText("Hello World");
        System.out.println("Current Text: " + textEditor.getText());
        history.push(textEditor.save());

        // Make some changes
        textEditor.setText("Hello World!!!");
        System.out.println("Current Text: " + textEditor.getText());
        history.push(textEditor.save());

        // More changes
        textEditor.setText("Hello!!!");
        System.out.println("Current Text: " + textEditor.getText());

        // Undo last change
        Memento lastStage = history.pop();
        textEditor.restore(lastStage);
        System.out.println("After Undo: " + textEditor.getText());

        // Undo to initial state
        lastStage = history.pop();
        textEditor.restore(lastStage);
        System.out.println("After Undo: " + textEditor.getText());
    }
}
