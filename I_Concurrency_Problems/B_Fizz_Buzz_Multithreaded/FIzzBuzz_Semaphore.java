package I_Concurrency_Problems.B_Fizz_Buzz_Multithreaded;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    private final Semaphore fizzSemaphore = new Semaphore(0);
    private final Semaphore buzzSemaphore = new Semaphore(0);
    private final Semaphore fizzbuzzSemaphore = new Semaphore(0);
    private final Semaphore numberSemaphore = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i=3;i<=n; i+=3){
            if(i%5!=0){
                fizzSemaphore.acquire();
                printFizz.run();
                numberSemaphore.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i=5; i<=n; i+=5){
            if(i%3!=0){
                buzzSemaphore.acquire();
                printBuzz.run();
                numberSemaphore.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i=15; i<=n; i+=15){
            fizzbuzzSemaphore.acquire();
            printFizzBuzz.run();
            numberSemaphore.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++){

            numberSemaphore.acquire(); // blocking state

            if((i%3==0 && i%5==0)){
                fizzbuzzSemaphore.release();
            }else if(i%3==0){
                fizzSemaphore.release();
            }else if(i%5==0){
                buzzSemaphore.release();
            }else{
                printNumber.accept(i);
                numberSemaphore.release();
            }
        }
    }
}

// There is no shared state by default in this problem.
// Order needs to be maintained like in ZeroEven Odd
// Could we use semaphores here too? 
// How many semaphores would we need? There are 4 threads / states here so 4 semaphores.
//Start with number semaphore having 1 permit and others 0 permits. But then how will it print String if we are using Number semaphore?
//  

// Why the code is not giving correct output? Reason: Because after printing fizz/buzz/fizzbuzz, the control is not going back to number thread. It is stuck there.

/* 

class FizzBuzz {
    private int n;

    private final Semaphore fizzSemaphore = new Semaphore(0);
    private final Semaphore buzzSemaphore = new Semaphore(0);
    private final Semaphore fizzbuzzSemaphore = new Semaphore(0);
    private final Semaphore numberSemaphore = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        fizzSemaphore.acquire();
        printFizz.run();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        buzzSemaphore.acquire();
        printBuzz.run();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    // For n=5 the output should be: 1 2 fizz 4 buzz. But the program hangs because the main thread is waiting for all 4 threads to complete. But the fizzbuzz thread is waiting for a permit which will never come because there is no number which is multiple of 3 and 5 in the range 1 to 5.
    // That's why in case of n=15, it doesn't hang (although order is wrong).
    /* Semaphore rule:
        - If permits are available: it takes one permit and continues execution.
        - If NO permits are available: the thread blocks/waits indefinitely until a permit becomes available
        - For n=5 try tryAcquire() instead of acquire(): it won't hang but the output is still wrong.
    

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        fizzbuzzSemaphore.acquire();
        printFizzBuzz.run();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++){
            if(i%3!=0 && i%5!=0){
                numberSemaphore.acquire();
                printNumber.accept(i);
                numberSemaphore.release(); //unlike zeroEvenOdd, we release here because the next migh again be a number(non deterministic). In zeroEvenOdd, after zero always comes odd/even(deterministic).
            }else if(i%3==0 && i%5==0){
                fizzbuzzSemaphore.release(); //this doesn't mean the iteration will move to fizzbuzz thread immediately. It just means that the fizzbuzz thread can now proceed when it gets scheduled. Thats why we need loop on other side too to ensure it prints whenver called
            }else if(i%3==0 && i%5!=0){
                fizzSemaphore.release();
            }else{
                buzzSemaphore.release();
            }
        }
    }
}

*/