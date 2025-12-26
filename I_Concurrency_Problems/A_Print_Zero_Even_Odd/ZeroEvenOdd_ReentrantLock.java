package I_Concurrency_Problems.A_Print_Zero_Even_Odd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd_ReentrantLock {

    private int n;
    private int turn = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition zeroCond = lock.newCondition();
    private final Condition evenCond = lock.newCondition();
    private final Condition oddCond = lock.newCondition();
    
    public ZeroEvenOdd_ReentrantLock(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=0;i<n; i++){
            lock.lock();
            try {
                while(turn !=0 ){
                    zeroCond.await(); // can throw InterruptedException which can held the lock forever if we don't unlock in finally block
                }
                printNumber.accept(0);
                turn = ((i+1)%2 == 0) ? 2 : 1;
                // turn == 1 ? oddCond.signal() : evenCond.signal(); // The ternary operator (?:) requires expressions that return a value and signal() is a void method.
                if(turn == 1){
                    oddCond.signal();
                } else {
                    evenCond.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        /* Is Try block necessary here?
        - Yes, when using ReentranLock.
        - Doing lock.lock(); manually acquires the lock and unlike synchronized, it doesn't automatically release the lock when the method exits or something goes wrong.
        - So every lock.lock(); must be paired with a lock.unlock(); to ensure the lock is released.
        - With synchronized, the JVM automatically releases the lock when the synchronized block/method is exited, even if an exception occurs.
        
        */
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            lock.lock();
            try {
                while(turn != 2){
                    evenCond.await();
                }
                printNumber.accept(i);
                turn = 0;
                zeroCond.signal();
            } finally{
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            lock.lock();
            try {
                while(turn != 1){
                    oddCond.await();
                }
                printNumber.accept(i);
                turn = 0;
                zeroCond.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}