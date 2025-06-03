package B_OPPS.Bd_Static;

class Counter {
    // Static variables are shared across all instances of the class. There's only one copy of each static variable, regardless of how many objects you create.
    private static int count = 0;
    private int instanceId;

    public Counter(){
        count++;
        this.instanceId = count;
    }

    public static int getCount(){
        return count;
    }
    public int getInstanceId(){
        return instanceId;
    }
}

public class StaticVariables {
    public static void main(String[] args) {
        
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        // Accessing the static variable count directly
        System.out.println("Count: " + Counter.getCount()); 

        // Accessing the instance variable instanceId
        System.out.println("Instance ID of c1: " + c1.getInstanceId());
        System.out.println("Instance ID of c2: " + c2.getInstanceId());
        System.out.println("Instance ID of c3: " + c3.getInstanceId());
        
    }
}
