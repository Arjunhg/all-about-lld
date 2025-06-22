package B_OPPS.Bj_Generics.C_Wildcards;

import java.util.Arrays;
import java.util.List;

/*
 * ? extends Type
 * Restricts the type to Type or its subclasses
 * Useful for read-only operations where the specific type is not needed
 */

public class B_UpperBounded {

    static void printNumbers(List<? extends Number> list){
        for(Number num : list){
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3);
        printNumbers(intList);

        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        printNumbers(doubleList);

        List<String> stringList = Arrays.asList("A", "B", "C");
        // printNumbers(stringList); // This will cause a compile-time error
        // because String is not a subclass of Number
    }
    
}
