## 💡 Real-World Analogy: Your Text Editor's "Time Machine"

• **Picture this scenario** → You're typing away in your favorite text editor, making changes left and right
• **The moment of panic** → You accidentally delete an important paragraph or make unwanted changes
• **The lifesaver** → You simply hit Ctrl+Z (undo) and *voilà!* your content is back to its previous state
• **The magic behind it** → Your editor created invisible "snapshots" of your work without you even knowing

### 🔍 How This "Time Machine" Actually Works:

• **Smart Backup System** → Instead of cluttering your editor with complex backup logic, it simply saves lightweight "snapshots"
• **Instant Restoration** → When you need to go back, it retrieves that exact state in milliseconds
• **Clean Architecture** → This happens without breaking encapsulation or exposing internal details
• **Zero Interference** → Your main editing experience remains smooth and uninterrupted

### 🎯 The Pattern Behind the Magic:

• **Memory Keeper** → Acts like a digital photo album storing your document's state at different moments
• **Time Capsule** → Preserves the exact snapshot of your content, formatting, and cursor position
• **Restoration Tool** → Enables seamless time travel back to any previously saved state
• **State Guardian** → Protects and manages your document's history without cluttering the main editor

---

## 🔥 Challenge Accepted: Building Advanced Editor Features

**💭 The Interviewer's Curveball:** *"What if users want both undo AND redo functionality, plus detailed state information?"*

### 💪 Extensibility Superpowers:

• **Redo Magic** → Extend your history manager with forward-navigation capabilities
• **Rich State Data** → Store not just text, but formatting, timestamps, and user metadata
• **Core Stability** → Your main `TextEditor` class stays completely unchanged
• **External Management** → All history logic lives outside,

• **Manage state externally** → All history logic stays in the caretaker

---

## 🛠️ Implementation: Enhanced Editor with Undo/Redo

### Key Features of Our Enhanced `EditorHistory`:

• Dual Stack System:

    - undo: stack → Stores previous states  
    - redo: stack → Stores undone changes  

• Smart State Management:

    - Save State → Adds to undo stack, clears redo stack  
    - Undo Operation → Moves current to redo, restores from undo  
    - Redo Operation → Moves current to undo, restores from redo  


```java
public class EditorHistory{
        
        private Stack<Memento> undo = new Stack<>();
        private Stack<Memento> redo = new Stack<>();

        // When it's saved we don't want to add it to redo stack
        public void saveState(Memento memento){
                undo.push(memento);
                redo.clear();
        }

        // push the current state to the redo stack and pop the last state from undo stack to restore it
        // Pop from last state and restore it - Before restoring, push the current state onto redo stack
        public Memento undo(Memento memento){
                if(!undo.isEmpty()){
                        redo.push(memento);
                        return undo.pop();
                }
                return null;
        }

        public Memento redo(Memento memento){
                if(!redo.isEmpty()){
                        undo.push(memento);
                        return redo.pop();
                }
                return null;
        }
}
```

### 🎮 Interactive Demo: Watch It in Action

```java
public class MementoRedoDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();
        
        // 📝 Initial state
        editor.setText("Hello");
        history.saveState(editor.save());
        
        // ✏️ First change
        editor.setText("Hello, World!");
        history.saveState(editor.save());
        
        // 🖊️ Second change
        editor.setText("Hello, World! Welcome!");
        System.out.println("Current: " + editor.getText());
        
        // ↩️ Undo the last change
        Memento previousState = history.undo(editor.save());
        if (previousState != null) {
            editor.restore(previousState);
            System.out.println("After undo: " + editor.getText());
        }
        
        // ↪️ Redo the undone change
        Memento redoState = history.redo(editor.save());
        if (redoState != null) {
            editor.restore(redoState);
            System.out.println("After redo: " + editor.getText());
        }
    }
}
```

### 🎯 Expected Output Flow:
1. **Current:** `"Hello, World! Welcome!"`
2. **After undo:** `"Hello, World!"`
3. **After redo:** `"Hello, World! Welcome!"`

## Memory Magic: Key Advantages of the Memento Pattern ✨

### 🔑 **Core Benefits:**

- **🤐 Encapsulated State**
    - Safely stores an object's state without exposing its internals
    - Maintains privacy and security of internal data

- **🔙 Simplified Undo**
    - Easily implements rollback functionality
    - One-click restoration capabilities

- **🧩 Separation of Concerns**
    - Keeps state management separate from core logic
    - Clean architecture and maintainable code

- **🚀 Effortless Recovery**
    - Enables quick restoration of previous states
    - Minimal performance overhead

---

## Real-Life Use Cases and Examples 🌍✨

### 🎮 **Game State Saving**
- **What it does:** Save a game's progress at critical checkpoints
- **Why it matters:** Players can resume from a previous state if they lose
- **Example:** RPG games saving before boss battles 💾

### 📝 **Form Data Recovery**
- **What it does:** Store the state of web forms automatically
- **Why it matters:** Users can restore their entries if they navigate away accidentally
- **Example:** Long registration forms or survey responses 🔄

### ⚙️ **Configuration Management**
- **What it does:** Save configurations/settings before making changes
- **Why it matters:** Easy revert capability if something goes wrong
- **Example:** System settings, app preferences, or deployment configs 🔙

### 💰 **Financial Transactions**
- **What it does:** Maintain snapshots of account states before transactions
- **Why it matters:** Support rollbacks in case of errors or disputes
- **Example:** Banking systems, payment processors, trading platforms 📉