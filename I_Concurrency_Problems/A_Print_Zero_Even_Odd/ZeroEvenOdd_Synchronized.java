package I_Concurrency_Problems.A_Print_Zero_Even_Odd;

import java.util.function.IntConsumer;



class ZeroEvenOdd_Synchronized {
    private int n;
    private int turn = 0; // 0 -> zero's turn, 2 -> even's turn, 1 -> odd's turn
    
    public ZeroEvenOdd_Synchronized(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        synchronized(this){
            for(int i=0; i<n; i++){ //if =n, it will print extra zero
                while(turn != 0){
                    wait();
                }
                printNumber.accept(0);
                turn = ((i+1) % 2 == 0) ? 2 : 1; // even turn for next, odd turn for next
                notifyAll();
                //notify() wakes a thread at random, you must use notifyAll() to ensure all waiting threads check the state variable to see if it is finally their turn
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        synchronized(this){
            for(int i=2; i<=n; i+=2){
                while(turn != 2){
                    wait();
                }
                printNumber.accept(i);
                // if(i==n) { //Reduntant checks
                //     return;
                // }
                turn = 0;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        synchronized(this){
            for(int i=1; i<=n; i+=2){
                while(turn != 1){
                    wait();
                }
                printNumber.accept(i);
                // if(i==n) {
                //     return;
                // }
                turn = 0;
                notifyAll();
            }
        }
    }
}

/*Example: n=2, output = 0102
- zero prints 0 -> turn = (0+1)%2 == 1 -> odd's turn
- odd prints 1 -> turn = 0 -> zero's turn
- zero prints 0 -> turn = (1+1)%2 == 0 -> even's turn
- even prints 2 -> turn = 0 -> zero's turn
- even does have notifyAll() which makes turn = 0, but at this point i = n, so zero exits the loop and the thread ends.
*/