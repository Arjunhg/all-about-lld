package I_Concurrency_Problems.B_Fizz_Buzz_Multithreaded;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

// In reentrant lock we use 4 locks and one turn variable. Unlike synchronized which notify all thread at once we can use reentrant to notify specific thread only

public class FizzBuzz_ReentrantLock {

    private int n;
    private int turn = 1; // 1 - number, 2 - fizz, 3 - buzz, 4 - fizzbuzz

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition numberCond = lock.newCondition();
    private final Condition fizzCond = lock.newCondition();
    private final Condition buzzCond = lock.newCondition();
    private final Condition fizzbuzzCond = lock.newCondition();

    public FizzBuzz_ReentrantLock(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i=3; i<=n; i+=3){
            if(i%5!=0){
                lock.lock();
                try{
                    while(turn != 2){
                        fizzCond.await();
                    }
                    printFizz.run();
                    turn = 1;
                    numberCond.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i=5;i<=n; i+=5){
            if(i%3!=0){
                lock.lock();
                try {
                    while(turn != 3){
                        buzzCond.await();
                    }
                    printBuzz.run();
                    turn = 1;
                    numberCond.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i=15; i<=n; i+=15){
            lock.lock();
            try {
                while(turn != 4){
                    fizzbuzzCond.await();
                }
                printFizzBuzz.run();
                turn = 1;
                numberCond.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++){
            lock.lock();
            try {
                while(turn != 1){
                    numberCond.await();
                }
                if(i%3==0 && i%5==0){
                    turn = 4;
                    fizzbuzzCond.signal();
                } else if(i%3==0){
                    turn = 2;
                    fizzCond.signal();
                } else if(i%5==0){
                    turn = 3;
                    buzzCond.signal();
                } else {
                    printNumber.accept(i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
