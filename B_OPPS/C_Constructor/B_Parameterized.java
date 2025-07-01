package B_OPPS.C_Constructor;

class Movie {
    private String title; 
    private int duration; 

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public void displayDetails(){
        System.out.println("Title: " + title + ", Duration: " + duration + " minutes");
    }
}


public class B_Parameterized {
    public static void main(String[] args) {
        
        Movie movie1 = new Movie("Inception", 148); // parameterized constructor
        movie1.displayDetails(); // prints Inception and 148

        Movie movie2 = new Movie("The Matrix", 136); 
        movie2.displayDetails();
    }
}
