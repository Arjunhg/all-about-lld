package B_OPPS.C_Constructor;

class Movie {
    private String name;
    private int duration;

    public Movie(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Movie(Movie other){
        this.name = other.name;
        this.duration = other.duration;
    }
    
    public void displayDetails(){
        System.out.println("Title: " + name + ", Duration: " + duration + " mins");
    }
}

public class C_Copy {
    public static void main(String[] args) {
        
        Movie original = new Movie("Incpetion", 148);

        Movie copy = new Movie(original); 
        copy.displayDetails(); 
    }
}
