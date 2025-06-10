package B_OPPS.Be_This;

class Person {
    private String name;
    private int age;

    /*1: Refering to Instance Object
    Person(String name){
        this.name = name;
    }
    */

    /*2: Contructor Chaining
    Person(String name){
        this(name, 0);
    }
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    */

    /*3: Returning Current Object: Used in fluent interfaces and builder patterns
    Person setName(String name){
        this.name = name;
        return this; //Enable method chaining
    }
    */

    // 4: Passing Current Object to another method
    void greet(Person person){
        System.out.println("Hello, " + person);
    }
    void introduce(){
        greet(this); //Passing current object to another method
    }
    // Overrinding toString() method to print proper name instead of hashcode
    public String toString(){
        return "I am in Person instance";
    }

    void display(){
        String name = "Local Name";
        System.out.println("Local Name: " + name);
        //1: System.out.println("Instance Name: " + this.name);
        //2: System.out.println("Instance Name: " + this.name + ", Age: " + this.age);
        //3: System.out.println("Instance Name: " + this.name);
    }
}

public class Example {
    public static void main(String[] args) {
        /* 1,2: 
        Person p = new Person("John");
        p.display();
        */

        /*3:
        Person p = new Person();
        p.setName("John").display(); //p.setName("John") returns p object again to enable method chaining and that's why we can use display() method
        */

        //4:
        Person p = new Person();
        p.introduce();
    }
}
