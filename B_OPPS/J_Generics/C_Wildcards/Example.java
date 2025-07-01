package B_OPPS.J_Generics.C_Wildcards;
import java.util.Arrays;
import java.util.List;

public class Example {
    
    static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        List<String> stringList =Arrays.asList("Apple", "Banana", "Cherry");
        printList(stringList);

        List<Integer> intList = Arrays.asList(1, 2, 3);
        printList(intList);
    }
}
