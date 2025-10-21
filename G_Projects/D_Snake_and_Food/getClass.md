
## ✅ What `getClass()` Does:

_(Explains getClass() defined in Pair.java)_

```java
getClass()
```

is a method inherited from Java’s root `Object` class, and it returns a **runtime class object** (an instance of `Class<?>`) that represents the **actual class** of the object.

### 🔍 Example:

```java
Pair p = new Pair(2, 3);
System.out.println(p.getClass());
```

Output:

```
class G_Projects.D_Snake_and_Food.Utility.Pair
```

---

## 🧠 In `equals()`, how is `getClass()` used?

You often see code like this:

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair pair = (Pair) o;
    return row == pair.row && col == pair.col;
}
```

### 🧩 What is it checking?

```java
getClass() != o.getClass()
```

Means:

> “Are these two objects **exactly the same type**?”

---

## 🔄 Why use `getClass()` instead of `instanceof`?

Good question — here's the key difference:

### ✅ `getClass()` → **strict type match**

```java
if (getClass() != o.getClass()) return false;
```

* Ensures both objects are **exactly** the same class.
* `Pair` compared only with `Pair`, not with subclasses.

### ⚠️ `instanceof` → **allows subclasses**

```java
if (!(o instanceof Pair)) return false;
```

* Allows subclasses of `Pair` to be treated as `Pair`.
* Can lead to **symmetry issues** in `equals()` if not handled carefully.

---

### 🔍 Symmetry problem example:

```java
class A {
    int x;
    public boolean equals(Object o) {
        if (o instanceof A) {
            return x == ((A) o).x;
        }
        return false;
    }
}

class B extends A {
    int y;
    public boolean equals(Object o) {
        if (o instanceof B) {
            return super.equals(o) && y == ((B) o).y;
        }
        return false;
    }
}
```

Now if:

```java
A a = new A();
B b = new B();
a.equals(b); // true
b.equals(a); // false
```

⚠️ **Breaks symmetry**, which violates Java’s `equals()` contract.

Using `getClass()` avoids this by forcing strict type comparison.

---

## ✅ Summary

| Method       | Behavior                         | Use when...                                 |
| ------------ | -------------------------------- | ------------------------------------------- |
| `getClass()` | Checks for **exact class match** | You want strict type equality (like `Pair`) |
| `instanceof` | Allows **subclass matching**     | You support inheritance in `equals()`       |

---

### 📌 In your `Pair` class:

```java
if (o == null || getClass() != o.getClass()) return false;
```

This ensures:

* You don't compare `Pair` with any subclass (like `ExtendedPair`)
* You preserve symmetry and consistency

---
