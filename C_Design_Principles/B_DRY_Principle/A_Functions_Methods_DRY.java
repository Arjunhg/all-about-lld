package C_Design_Principles.B_DRY_Principle;

class NumberSwapper {
    // Voilating DRY Principle
    void processNumbersNonDry() {
        int a = 5;
        int b = 10;
        // swap
        int temp = a;
        a = b;
        b = temp;

        int x = 20;
        int y = 30;
        // swap
        temp = x;
        x = y;
        y = temp;
    }

    // Following DRY Principle
    void swap(int[] numbers){
        if(numbers.length >= 2){
            int temp = numbers[0];
            numbers[0] = numbers[1];
            numbers[1] = temp;
        }
    }
}

public class A_Functions_Methods_DRY {

    public static void main(String[] args) {
        NumberSwapper swapper = new NumberSwapper();
        int[] numbers = {5, 10};
        swapper.swap(numbers);
        System.out.println("Swapped numbers: " + numbers[0] + ", " + numbers[1]);
    }
    
}
