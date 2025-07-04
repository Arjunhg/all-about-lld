package C_Design_Principles.C_KISS_Principle;

// Poorly written code: Violates KISS Principle
class BadFactorial {
    static int factorial(int n){

        if(n < 0) throw new IllegalArgumentException("Negative numbers not allowed");
        if(n==1) return 1;

        int fact = 1;
        for(int i=1; i<=n; i++){
            int temp = 1; // Temporary variable to store intermediate results
            for(int j=1; j<=i; j++){
                temp *= j;
            }
            fact = temp; // Reassigning fact unnecessarily
        }
        
        return fact;
    }
}

// Well-written code: Follows KISS Principle
class GoodFactorial {
    static int factorial(int n){
        if(n<0) throw new IllegalArgumentException("Negative numbers not allowed");
        if(n==0 || n==1) return 1; 

        int fact = 1;
        for(int i=1; i<=n; i++){
            fact *= i;
        }

        return fact; 
    }
}

public class Example {
    
    public static void main(String[] args) {
        // Using the factorial method
        try {
            System.out.println("Factorial of 5: " + BadFactorial.factorial(5)); // Should print 120
            System.out.println("Factorial of -1: " + BadFactorial.factorial(-1)); // Should throw exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Using the improved factorial method
        try {
            System.out.println("Factorial of 5: " + GoodFactorial.factorial(5)); // Should print 120
            System.out.println("Factorial of -1: " + GoodFactorial.factorial(-1)); // Should throw exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
