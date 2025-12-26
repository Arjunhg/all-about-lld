package I_Concurrency_Problems.A_Print_Zero_Even_Odd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/* We don't require locks in this case because:
- There is no shared mutable state. (n is shared but immutable)
- No shared counter or turn variable for semaphore
- Ordering: zero → odd/even → zero → odd/even → ... Only one thread is runnable at any time, enforced by semaphores. So there is no opportunity for a race condition.
- This problem is not about protecting data, it’s about ordering execution.
- Semaphore = “permission to proceed”, Lock = “exclusive access to data”
*/

public class ZeroEvenOdd_Semaphore {
    
    private int n;
    //only one permit allowed globally. This ensures no overlap, no races, ordering
    private final Semaphore zero = new Semaphore(1); // Start with 1 permit for zero. Zero thread may start immediately
    private final Semaphore even = new Semaphore(0); // Odd thread must wait
    private final Semaphore odd = new Semaphore(0);  // Even thread must wait

    public ZeroEvenOdd_Semaphore(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=0; i<n; i++){
            zero.acquire(); // Acquire permits to print zero
            printNumber.accept(0);
            if((i+1)%2==0){
                even.release(); // Allow even to proceed
            }else{
                odd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}
