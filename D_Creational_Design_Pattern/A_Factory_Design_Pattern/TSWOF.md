# Problems with Manual Object Creation in Java (Without Factory Pattern)

This document explains the issues with creating `Vehicle` objects directly within business logic classes in a Java system.

## 🚫 Current Code Issues

Each business logic class (e.g., `DeliveryService`, `TaxiService`) is directly instantiating vehicle objects like `Car`, `Truck`, and `Bike`.


## ❌ Why This Is a Problem

### 1. Vehicle Creation Logic Is Duplicated

Same vehicle creation code is repeated across multiple classes:

```java
// In DeliveryService
if (deliveryType.equals("standard")) {
    deliveryVehicle = new Car("Honda Civic");
}

// In TaxiService
if (rideType.equals("economy")) {
    taxiVehicle = new Car("Toyota Corolla");
}
````

#### ❗ Consequence:

If the logic needs to change, it must be updated everywhere.

---

### 2. Logging Requires Changing Multiple Classes

To log vehicle creation, you must modify each class:

```java
System.out.println("Creating Car: Honda Civic");
deliveryVehicle = new Car("Honda Civic");
```

#### ❗ Consequence:

Tedious and error-prone; no central place to control behavior.

---

### 3. Adding New Vehicle Type Requires Multiple Changes

When adding `ElectricCar`, every class must be updated:

```java
if (deliveryType.equals("eco")) {
    deliveryVehicle = new ElectricCar("Tesla Model 3");
}
```

#### ❗ Consequence:

Breaks Open/Closed Principle — code isn't easily extendable.

---

### 4. Constructor Parameter Changes Break All Callers

If a constructor changes:

```java
public Car(String model, String engineType) { ... }
```

Then all instantiations like:

```java
new Car("Honda Civic");
```

Need to be updated:

```java
new Car("Honda Civic", "V6");
```

#### ❗ Consequence:

Lots of changes across the codebase — brittle design.

---

### 5. No Centralized Control Over Vehicle Initialization

Each service creates vehicles differently, making global rules (e.g., registration, logging, validation) hard to apply.

#### ❗ Consequence:

Cannot enforce consistent behavior or inject shared setup.

---

### 6. Testing Becomes Difficult

Hardcoded creation prevents mocking in unit tests:

```java
// Can't inject mock vehicle because it's hardcoded:
taxiVehicle = new Car("BMW X5");
```

#### ❗ Consequence:

Violates Dependency Inversion Principle; poor testability.

---

### 7. DRY Principle Is Violated

Same logic is repeated in multiple classes:

```java
if (type.equals("X")) {
    new Car(...);
} else if (type.equals("Y")) {
    new Truck(...);
}
```

#### ❗ Consequence:

More room for bugs, harder to maintain, and violates best practices.

---

## ✅ Summary

| Problem # | Issue                          | Consequence               |
| --------- | ------------------------------ | ------------------------- |
| 1         | Duplicated logic               | Repetitive, error-prone   |
| 2         | Logging in multiple places     | Not maintainable          |
| 3         | Add vehicle = multiple changes | Violates Open/Closed      |
| 4         | Constructor change             | Fragile to updates        |
| 5         | No central control             | Inconsistent behavior     |
| 6         | Testing is hard                | Can't mock easily         |
| 7         | DRY Violation                  | More bugs and maintenance |

---

## ✅ Solution: Use Factory Pattern

Move all vehicle creation logic into a `VehicleFactory` class, and delegate instantiation responsibilities there.

