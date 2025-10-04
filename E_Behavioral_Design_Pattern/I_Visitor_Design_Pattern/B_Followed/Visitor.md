# 🏥 VISITOR DESIGN PATTERN - Hospital Scenario 🏥

Welcome to our interactive hospital simulation! Here's what makes this pattern amazing:

## 📋 THE SCENARIO:
• **Different types of patients** visit our hospital  
• **Specialized doctors** (our "visitors") perform various operations  
• **Operations include**: diagnosis, billing, treatment planning, etc.

## 🎯 THE PROBLEM WE'RE SOLVING:
• We **don't want to burden** each patient class with all possible operations  
• **Adding new operations** shouldn't require modifying existing patient classes  
• We want to **keep operations organized** by specialist (visitor type)

## ✨ THE VISITOR PATTERN MAGIC:
• **Separates operations** from the objects they act upon  
• **Doctors (visitors)** come to the patients and perform their specialized work  
• **Easy to add** new types of doctors without changing patient classes  
• **Clean separation** of concerns - each doctor knows their job!

---

## 🤔 Why "Visitor"? The Doctor's Visit Analogy 👨‍⚕️👩‍⚕️

The pattern is called **Visitor** because, much like a doctor who visits different patients to perform specialized operations, a visitor object **"visits"** each element (in our case, a patient) to carry out an operation. 

• Instead of each patient class having a pile of methods, **the visitor comes in and does its work**  
• It's like having a **mobile doctor** who doesn't require every patient to know everything about healthcare! 😎

---

## 🔍 Difference between strategy, chain of responsibility and visitor design pattern:

### **Strategy Pattern**
- **When**: You have **one object** and want to **switch between different behaviors/algorithms** at runtime
- **Analogy**: Like choosing different tools for the same job - hammer, screwdriver, or wrench for fixing something
- **Key**: One object, multiple possible behaviors that you can swap

### **Chain of Responsibility**  
- **When**: You want to **pass a request through a series of handlers** until one handles it
- **Analogy**: Like customer support - if first agent can't help, they transfer to specialist, then to manager, etc.
- **Key**: Linear chain where request moves until handled

### **Visitor Pattern**
- **When**: You have a **complex structure** (tree/graph) with many different types of objects and want to **perform operations** on all of them
- **Analogy**: Like an inspector visiting every room in a building - each room might be different (office, kitchen, bathroom) but inspector can examine all
- **Key**: Complex hierarchy where you want to apply operations without modifying the original objects

## Simple Mental Models:

- **Strategy**: "Which tool should I use for this one job?"
- **Chain of Responsibility**: "Who can handle this request in my team?"  
- **Visitor**: "How can I inspect every part of this complex structure?"
