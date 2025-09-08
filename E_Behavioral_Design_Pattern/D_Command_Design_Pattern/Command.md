# 🛠️ Understanding the Command Design Pattern

The **Command Design Pattern** is a **behavioral design pattern** used to encapsulate a request as an object. This encapsulation allows us to:

- Parameterize objects with operations
- Delay execution
- Queue or log requests
- Support undoable operations

Think of it like giving someone a **"to-do list"** where each item is a specific action to be performed — but they don’t have to do it right away.

---

## 📝 Why Is It Called the "Command" Pattern?

It’s called the **Command** pattern because it centralizes the concept of commanding an action. Instead of invoking a method directly, you **wrap the action in a command object**. That command can then be:

- Stored
- Passed around
- Executed later

You essentially decouple **what needs to be done** from **when or how it is done**.

---

## 🧱 Components of the Command Pattern

### 1. **Command Interface**
Defines the standard method `execute()` that all commands must implement.

```java
public interface Command {
    void execute();
}
````

---

### 2. **Concrete Command Classes**

Each command class encapsulates a single action. They call specific methods on the **receiver** (usually a device or service).

```java
public class TurnOnCommand implements Command {
    private TV tv;

    public TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.turnOn();
    }
}
```

Other examples: `TurnOffCommand`, `ChangeChannelCommand`, `AdjustVolumeCommand`.

---

### 3. **Receiver**

This is the object that performs the actual operations.

```java
public class TV {
    public void turnOn() { System.out.println("TV turned on"); }
    public void turnOff() { System.out.println("TV turned off"); }
    public void changeChannel(int channel) {
        System.out.println("Changing channel to " + channel);
    }
    public void adjustVolume(int volume) {
        System.out.println("Setting volume to " + volume);
    }
}
```

---

### 4. **Invoker (RemoteControl)**

The invoker triggers the command. It doesn’t need to know what the command does internally.

```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

---

### 5. **Client**

The client sets everything up — creates receivers, commands, and passes commands to the invoker.

```java
public class Main {
    public static void main(String[] args) {
        TV tv = new TV();

        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
```

---

## 🚀 Advantages of the Command Pattern

* ✅ **No Need to Modify Existing Code**
  New commands can be added without changing the `RemoteControl`.

* ✅ **Separation of Concerns**
  Command logic is in its own class — easier to maintain and test.

* ✅ **Flexible Execution**
  Commands can be queued, logged, or executed conditionally or at different times.

---

## 🌍 Real-World Use Cases

* **Undo/Redo** in text editors
* **GUI buttons** mapped to actions
* **Task scheduling** or **job queues**
* **Macro recording** (e.g., Photoshop automation)

---

# 🔄 Can We Improve the Pattern? YES!

While the standard implementation is solid, you might notice the **client is doing too much**:

```java
TV tv = new TV();
Command turnOn = new TurnOnCommand(tv);
Command changeChannel = new ChangeChannelCommand(tv, 5);
```

This tight coupling means:

* The client **knows all concrete classes**
* The client **manages parameters**
* Code becomes **repetitive and harder to scale**

---

## 🧠 So, Can We Make It More Like the Iterator Pattern?

Yes! Just like how we get an iterator with `playlist.iterator()`, we can create a **factory-style design** where the `RemoteControl` class **generates commands** for us.

---

## ✨ Improved Command Pattern Design

Let’s refactor the `RemoteControl` to act as a **factory** and **command manager**.

### 🧰 1. Factory Methods Inside RemoteControl

```java
public class RemoteControl {
    private TV tv;
    private Map<String, Command> commands = new HashMap<>();

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    // Factory-like methods
    public Command createTurnOnCommand() {
        return new TurnOnCommand(tv);
    }

    public Command createTurnOffCommand() {
        return new TurnOffCommand(tv);
    }

    public Command createChangeChannelCommand(int channel) {
        return new ChangeChannelCommand(tv, channel);
    }

    public Command createAdjustVolumeCommand(int volume) {
        return new AdjustVolumeCommand(tv, volume);
    }

    // Store and execute
    public void setCommand(String key, Command command) {
        commands.put(key, command);
    }

    public void executeCommand(String key) {
        if (commands.containsKey(key)) {
            commands.get(key).execute();
        } else {
            System.out.println("No such command: " + key);
        }
    }
}
```

---

### 🧪 2. Main Class Now Looks Much Cleaner

```java
public class Main {
    public static void main(String[] args) {
        TV tv = new TV();
        RemoteControl remote = new RemoteControl(tv);

        remote.setCommand("turnOn", remote.createTurnOnCommand());
        remote.setCommand("changeTo5", remote.createChangeChannelCommand(5));
        remote.setCommand("volume20", remote.createAdjustVolumeCommand(20));
        remote.setCommand("turnOff", remote.createTurnOffCommand());

        remote.executeCommand("turnOn");
        remote.executeCommand("changeTo5");
        remote.executeCommand("volume20");
        remote.executeCommand("turnOff");
    }
}
```

This design is **cleaner**, **centralized**, and much more **scalable**.

---

### 🧑‍🔧 Bonus: Dynamic Command Creation

Want something even more flexible? Add a **generic factory method**:

```java
public Command createCommand(String type, Object... args) {
    switch (type) {
        case "turnOn":
            return new TurnOnCommand(tv);
        case "turnOff":
            return new TurnOffCommand(tv);
        case "changeChannel":
            return new ChangeChannelCommand(tv, (Integer) args[0]);
        case "adjustVolume":
            return new AdjustVolumeCommand(tv, (Integer) args[0]);
        default:
            throw new IllegalArgumentException("Unknown command: " + type);
    }
}
```

This keeps command creation logic even more dynamic and reusable.

---

## 🧩 When Should You Stick to the Original Design?

While the improved design is great for abstraction and scaling, the original setup is still useful when:

* Commands need **highly variable parameters**
* You want to **reuse specific command objects**
* Commands involve **complex setup** outside what a simple factory can handle

---

## 🏁 Final Thoughts

The Command pattern is **powerful**, but also **flexible**. Just like you observed from the Iterator pattern, **you can evolve traditional patterns into more modern and intuitive designs**.

> Design patterns are **templates**, not rules. Adapt them to fit your architecture, your team’s needs, and the complexity of your system.

---

