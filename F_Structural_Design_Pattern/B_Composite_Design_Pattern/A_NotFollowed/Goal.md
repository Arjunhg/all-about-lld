# Problem Statement: Managing Hierarchies of Devices 🏠

Imagine you're designing a **smart home system**. Your goal is to control various devices such as rooms and individual appliances within a smart home.

## Example Scenario

Consider this hierarchy:

- **🏠 House**
    - **🏢 Floor 1**
        - **🛏️ Bedroom**
            - 💡 Smart Light
            - ❄️ Air Conditioner
            - ☕ Coffee Machine
        - **🍽️ Kitchen**
            - 💡 Smart Light
            - 🔥 Smart Oven
    - **🏢 Floor 2**
        - **🛁 Bathroom**
            - 💡 Smart Light
            - 🚿 Smart Shower

## The Challenge: Managing Hierarchical Structures 🔗

The core problem emerges when dealing with nested device hierarchies:

### 1. **Multi-level Containment**
- A `Room` contains multiple devices (e.g., `AirConditioner`, `SmartLight`)
- A `Floor` contains multiple `Room` objects
- The `House` contains multiple `Floor` objects

### 2. **Unified Control Requirements**
Your `SmartHomeController` needs to control devices at **any level**:

| Action | Expected Behavior |
|--------|------------------|
| Turn off individual light | Only that specific light turns off |
| Turn off entire room | All devices in that room turn off |
| Turn off whole house | **All devices** across all floors and rooms turn off |

### 3. **Complex Interaction Patterns**
The app must interact with this hierarchy seamlessly:

```
house.turnOff() 
    ↓
floor1.turnOff() + floor2.turnOff()
    ↓
bedroom.turnOff() + kitchen.turnOff() + bathroom.turnOff()
    ↓
light.turnOff() + airConditioner.turnOff() + coffeeMachine.turnOff() + ...
```

---

> **Current Approach**: This folder demonstrates the traditional way to solve this hierarchical control problem.