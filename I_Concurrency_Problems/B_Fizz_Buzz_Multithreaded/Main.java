package I_Concurrency_Problems.B_Fizz_Buzz_Multithreaded;

public class Main {
    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(5);
        
        Thread t1 = new Thread(() -> {
            try {
                fizzBuzz.fizz(System.out::println);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }, "fizz-thread");

        Thread t2 = new Thread(() -> {
            try {
                fizzBuzz.buzz(System.out::println);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }, "buzz-thread");

        Thread t3 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(System.out::println);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }, "fizzbuzz-thread");

        Thread t4 = new Thread(() -> {
            try {
                fizzBuzz.number(System.out::println);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }, "number-thread");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
