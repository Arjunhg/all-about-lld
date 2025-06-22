package B_OPPS.Bj_Generics.C_Wildcards;

import java.util.Arrays;
import java.util.List;

/*
 * Represents unknown type
 * Useful when type is not relevant to logic
 */

public class A_Unbounded {

    static void printList(List<?> list){
        for(Object item : list){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3);
        printList(intList);
    }
    
}
