package D_Creational_Design_Pattern.E_Builder_Design_Pattern;

class Car {
    private String engine;
    private int wheels;
    private String color;
    private int seats;
    private boolean sunroof;
    private boolean navigationSystem;

    public Car(String engine, int wheels, String color, int seats, boolean sunroof, boolean navigationSystem) {
        this.engine = engine;
        this.wheels = wheels;
        this.color = color;
        this.seats = seats ;
        this.sunroof = sunroof;
        this.navigationSystem = navigationSystem;
    }
}

/* Issues
 * 1-> When you need to set optional attributes, you have to pass values for all parameters which is unnecessary.
 * 2-> Even if you try to make multiple contructors for combinations of attribute, it can lead to a explosion of constructors.
 * 3-> Hard to read because a constructor that makes multiple paramteres is hard to read and understand what each parameter represents.
    E.g. Car("V8", 4, "Red", 5, true, false) is not clear what each parameter means.
 * 4-> To solve all this we use Builder Design Pattern: It is responsible for assembling an object.
 */

