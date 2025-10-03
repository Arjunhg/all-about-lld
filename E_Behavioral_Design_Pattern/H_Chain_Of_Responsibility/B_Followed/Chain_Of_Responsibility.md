# Chain of Responsibility Pattern 🏃‍♂️🏃‍♀️

## 🎯 What is it?
Imagine you're in a relay race—each runner passes the baton along until one of them is best suited to finish the race. That's exactly how this pattern works: **a request is passed along a chain of handlers until one of them takes care of it**.

## 🔄 Our Refactoring Journey
Let's refactor our solution using the Chain of Responsibility pattern. Instead of a single big function, we'll create a series of handler classes:

- ✅ Each handler checks if it can process the leave request
- ✅ If not, it passes the request along the chain

## 🛠️ Implementation Steps

### Step 1: Create Abstract Handler Class
- 📁 Create `Approver.java`

### Step 2: Create Concrete Chains
- 📁 Build concrete handlers in `01_Concrete_Approvers`

### Step 3: Create Demo Class
- 🧪 Set up testing environment

## 🤔 Alternative Approach: Interface vs Abstract Class

### 💡 Interface Approach
**What if we put `setNextApprover` inside each subclass?**

```java
// Approver as an interface
public interface Approver {
    void setNextApprover(Approver nextApprover);
    void processRequest(int leaveDays);
}

// Concrete implementation
public class Supervisor implements Approver {
    private Approver nextApprover;
    
    @Override
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }
    
    @Override
    public void processRequest(int leaveDays) {
        if (leaveDays <= 3) {
            System.out.println("Supervisor approves " + leaveDays + " days leave");
        } else if (nextApprover != null) {
            nextApprover.processRequest(leaveDays);
        }
    }
}
```

### ✅ Why Interface Approach Works
- **Valid Implementation** ✓ Each subclass maintains its own `nextApprover` field
- **Flexibility** ✓ Subclasses can implement chain logic differently if needed
- **No Inheritance** ✓ Uses interface implementation instead of class inheritance

### 🏆 Why Abstract Class is Generally Preferred

#### ❌ Interface Approach Drawbacks:
- **Code Duplication** → `setNextApprover` method repeated in every subclass
- **Maintenance Issues** → Changes require updating every subclass
- **Violates DRY Principle** → Same field and setter in every implementation

#### ✅ Abstract Class Benefits:
- **Consistent Behavior** → Chain of Responsibility has uniform "next handler" logic
- **Less Boilerplate** → Avoids repeating code across implementations
- **Better Maintenance** → Single place to update chain logic

## ⚖️ Trade-offs Summary

| Approach | Pros | Cons |
|----------|------|------|
| **Interface** | 🔧 More flexible | 📝 More boilerplate code |
| **Abstract Class** | 🎯 Less code duplication | 🔗 Tighter coupling |

## 🎯 Bottom Line
While both approaches work, the **Abstract Class approach** aligns better with the Chain of Responsibility pattern's intent and follows software engineering best practices!
