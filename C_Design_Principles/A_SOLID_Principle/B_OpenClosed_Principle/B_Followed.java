package C_Design_Principles.A_SOLID_Principle.B_OpenClosed_Principle;

abstract class Shape {
    abstract double calculateArea();
}

class Circle extends Shape {
    private double radius;

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    @Override
    double calculateArea() {
        return width * height;
    }
}

// If we want to add a new shape, like Triangle, we can do so without modifying existing code
class Triangle extends Shape {
    private double base;
    private double height;

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }
}

public class B_Followed {
    
}
