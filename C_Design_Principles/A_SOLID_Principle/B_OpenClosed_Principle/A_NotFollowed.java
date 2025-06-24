package C_Design_Principles.A_SOLID_Principle.B_OpenClosed_Principle;

/*
 *  Suppose we have a Shape class that calculates the area of different shapes. Initially, it supports only circles and rectangles. Adding a new shape, like a triangle, would require modifying the existing code.

    The Open/Closed Principle states that software entities should be open for extension but closed for modification. Here since we are modifying the existing code and not extending it, that is why the current approach is problematic:
 */

class Shape {
    
    private String type;
    public double calculateArea(){
        if(type.equals("circle")){
            // calculate area of circle
        }else if(type.equals("rectangle")){
            // calculate area of rectangle
        }

        // If we want to add a new shape, like triangle, we have to modify this code

        return 0.0; // Placeholder return value
    }
}

public class A_NotFollowed {
    
}
