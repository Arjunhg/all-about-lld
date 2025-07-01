package B_OPPS.C_Constructor;

class Movie {
    private String title; //default: null
    private int duration; //default: 0

    public void displayDetails(){
        System.out.println("Title: " + title + ", Duration: " + duration + " minutes");
    }
}

public class A_Default {
    public static void main(String[] args) {
        Movie movie1 = new Movie(); // default constructor
        movie1.displayDetails(); // prints null and 0

        /* 
            Movie movie2 = new Movie(); 
            movie2.title = "The Matrix";
            movie2.duration = 136;
            movie2.displayDetails(); 

            * title and duration are private, so the above code will not compile.
            * To access private members, we need to use getter and setter methods inside Movie class.
                public void setTitle(String title) {
                    this.title = title;
                }
                public void setDuration(int duration) {
                    this.duration = duration;
                }
            * Or make them public.
        */
    }
}
