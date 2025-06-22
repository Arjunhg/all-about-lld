package B_OPPS.Bj_Generics.C_Wildcards;

import java.util.ArrayList;
import java.util.List;

/*
 * ? super Type
 * Restricts the type to Type or its superclasses
 * Useful for write operations where you want to ensure type safety
 */

public class D_LowerBound {

    /*Not Allowed 
        static void addNumbers(List<? extends Integer> list){
            list.add(42);
        }

        // If allowed:
        List<? extends Integer> list = new ArrayList<Double>(); // hypothetically
        list.add(42); // 42 is an Integer, but the actual list is of Double? 
    */

    static void addNumbers(List<? super Integer> list){
        list.add(42);
    }

    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println("Added 42 to Number list: " + numberList);
    }
    
}
