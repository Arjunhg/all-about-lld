package D_Creational_Design_Pattern.E_Builder_Design_Pattern;

class Car {
    private String engine;
    private int wheels;
    private String color;
    private int seats;
    private boolean sunroof;
    private boolean navigationSystem;

    // Constructor that accepts a CarBuilder
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.seats = builder.seats;
        this.sunroof = builder.sunroof;
        this.navigationSystem = builder.navigationSystem;
    }

    public String getEngine(){
        return engine;
    }
    public int getWheels(){
        return wheels;
    }
    public String getColor(){
        return color;
    }
    public int getSeats(){
        return seats;
    }
    public boolean hasSunroof(){
        return sunroof;
    }
    public boolean hasNavigationSystem(){
        return navigationSystem;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheels=" + wheels +
                ", color='" + color + '\'' +
                ", seats=" + seats +
                ", sunroof=" + sunroof +
                ", navigationSystem=" + navigationSystem +
                '}';
    }

    // Nested Builder class as it's tightly coupled with Car and will be easier to access private members
    public static class CarBuilder {
        private String engine;
        private int wheels = 4; // Default value
        private String color = "Black"; 
        private int seats = 5; 
        private boolean sunroof = false; 
        private boolean navigationSystem = false; 

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }
        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }
        public CarBuilder setSeats(int seats) {
            this.seats = seats;
            return this;
        }
        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }
        public CarBuilder setNavigationSystem(boolean navigationSystem) {
            this.navigationSystem = navigationSystem;
            return this;
        }

        public Car build(){
            return new Car(this);
        }
    }
}

public class B_Followed {
    public static void main(String[] args) {
        
        Car.CarBuilder builder = new Car.CarBuilder();

        Car car1 = builder.setEngine("V8")
                .setWheels(4)
                .setColor("Red")
                .setSeats(5)
                .setSunroof(true)
                .setNavigationSystem(true)
                .build();
        System.out.println(car1);

        // Different configuration
        Car car2 = builder.setEngine("V6")
                .build();
        System.out.println(car2);
    }
}
