package I_Concurrency_Problems.B_Fizz_Buzz_Multithreaded;

import java.util.function.IntConsumer;

public class FizzBuzz_Synchronized {
    
    private int n;

    private final Object lock = new Object();
    private int turn = 1; // 

    public FizzBuzz_Synchronized(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized(lock){
            for(int i=3; i<=n; i+=3){
                if(i%5!=0){
                    while(turn!=2){
                        lock.wait();
                    }
                    printFizz.run();
                    turn = 1;
                    lock.notifyAll();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized(lock){
            for(int i=5; i<=n; i+=5){
                if(i%3!=0){
                    while(turn!=3){
                        lock.wait();
                    }
                    printBuzz.run();
                    turn = 1;
                    lock.notifyAll();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized(lock){
            for(int i=15; i<=n; i+=15){
                while(turn != 4){
                    lock.wait();
                }
                printFizzBuzz.run();
                turn = 1;
                lock.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized(lock){
            for(int i=1; i<=n; i++){
                while(turn != 1){
                    lock.wait();
                }
                if((i%3==0 && i%5==0)){
                    turn = 4;
                    lock.notifyAll();
                }else if(i%5==0){
                    turn = 3;
                    lock.notifyAll();
                }else if(i%3==0){
                    turn = 2;
                    lock.notifyAll();
                }else{
                    printNumber.accept(i);
                    lock.notifyAll();
                }
            }
        }
    }

}