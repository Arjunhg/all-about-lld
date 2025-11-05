## Understanding the Law of Demeter in Observer Design

> **Context:** Refer to ElevatorObserver.java to understand why this principle needs explanation

### Core Principle: Law of Demeter (Principle of Least Knowledge)

The fundamental question when designing observer interfaces is: **"What information does an observer need to react to this specific event?"**

#### Event-Driven Design Philosophy

- **Clear Intent**: When `onElevatorStateChange` is called, you're explicitly communicating what changed
- **Semantic Meaning**: Method signatures should clearly indicate the type of event that occurred
- **Direct Information**: Pass the new state as a parameter to tell observers "Here's exactly what changed"

#### The Alternative Approach Problems

When you only pass the `Elevator` object and force observers to call `elevator.getState()`:
- **Extra Work**: Observers must figure out what actually changed
- **Lost Semantics**: Method signature no longer communicates the event type clearly
- **Ambiguous Intent**: The notification purpose becomes unclear

### Practical Benefits of Explicit Parameters

#### Consistency Across Observers
- **Same State Value**: All observers receive identical state values at the moment of event firing
- **Race Condition Prevention**: Eliminates inconsistencies when elevator state changes rapidly between notifications
- **Predictable Behavior**: Ensures all observers work with the same data snapshot

#### Performance Considerations
- **Single Computation**: Calculate the state once and pass it to all observers
- **Reduced Method Calls**: Avoid multiple `getState()` calls (10 observers = 1 parameter pass vs. 10 method calls)
- **Efficiency**: Minor but measurable performance improvement

### When to Use Just the Elevator Object

#### Scenarios for Passing Only Elevator Reference

**Complex State Queries**: When observers need multiple pieces of information:
- Current state
- Current floor  
- Movement direction
- Request queue status

**Flexible Observer Design**: Single method approach like `onElevatorUpdate(Elevator elevator)`
- **Simplicity**: Reduces the number of notification methods
- **Flexibility**: Observers can query exactly what they need
- **Scalability**: Easier to manage when there are many change types

### Recommended Design for Elevator System

#### Keep Specific Parameters
- **Clear Purpose**: `onElevatorFloorChange` immediately communicates floor changes
- **Self-Documenting**: Code intent is explicit and readable
- **Event-Specific**: Each notification method has a distinct, clear purpose

#### Potential Enhancement to Test
Current design: `onElevatorStateChange(Elevator elevator, ElevatorState state)`

**Consider Enhanced Transition Information:**
```java
onElevatorStateChange(Elevator elevator, ElevatorState oldState, ElevatorState newState)
```

**Benefits of State Transition Parameters:**
- **Transition Awareness**: Observers can react based on specific state changes
- **Contextual Reactions**: Different behavior for `MOVING → IDLE` vs. `IDLE → MOVING`
- **Historical Context**: Observers understand both where the elevator came from and where it's going