

# Object-Oriented Programming (Java) - Exercise List

This repository contains solutions to Object-Oriented Programming (Java) exercises from the Computational Language Paradigms (CLP) 2024.2 course, demonstrating best practices such as polymorphism, generics, exception handling, and design patterns.

## Repository Structure
- `Q1/` - Cinema queue implementation using polymorphism and generics.
- `Q2/` - Coffee shop product management system with inheritance and exception handling.
- `Q3/` - Smart home system using interfaces.
- `Q4/` - Game monster behavior implementation using composition.

## How to Run
Each folder contains a `Main.java` file that can be executed in a Java development environment.

### Requirements
- Java 8+
- IDE such as IntelliJ, Eclipse, or VS Code (optional)

### Key Highlights:
- **Strong OOP principles:** Polymorphism, inheritance, composition, abstraction, and encapsulation.
- **Clean Code & Best Practices:** Generics, exception handling, and design patterns.
- **Real-World Applications:** Smart home systems, product management, and game mechanics.

## Conceptual Insights

### Question 3 - Why Use an Interface for `SmartDevice`?
> The `SmartDevice` interface was chosen to define a contract that all smart devices must follow without enforcing a rigid hierarchy. Unlike an abstract class, an interface allows any class to implement it freely, making the system more flexible and modular. Additionally, since Java does not support multiple class inheritance, using interfaces enables devices to inherit behavior from other classes when necessary.

### Question 4 - Design Choices for Colored Monsters
> The chosen approach uses **composition**, where colors (`Blue`, `Green`, etc.) are separate classes that apply effects to monsters instead of creating subclasses for each variation.

**Advantages:**
- **Modular & Scalable**: New colors can be added without modifying the `Monster` class.
- **Avoids Subclass Explosion**: No need for multiple subclasses like `BlueDragon` or `YellowWolf`.
- **Clearer Separation of Concerns**: Easier maintenance and code organization.

**Disadvantages:**
- **Requires an Extra Step**: Colors must be explicitly applied to monsters.
- **Direct Attribute Modification**: Color classes alter monster attributes, potentially leading to unexpected effects.
- **Limited Multi-Color Combinations**: Difficult to stack multiple colors dynamically.

> Alternative approaches include **inheritance** (simpler but less flexible) or the **Decorator pattern** (more dynamic but more complex). Overall, composition is a solid choice but could be refined to improve modification control.



