package I_Concurrency_Problems.A_Print_Zero_Even_Odd;

public class Main {
    public static void main(String[] args) {
        
        int n = 10;

        ZeroEvenOdd_Semaphore zeroEvenOdd = new ZeroEvenOdd_Semaphore(n);

        Thread t1 = new Thread(() -> {
            try { // zero throws checked exception but Runnable (functional interface) doesn't allow checked exceptions. To handle them we need to ensure they don't escape the run() method by catching them here
                zeroEvenOdd.zero(x -> System.out.println(x)); // It says pass x to lambda function () and print x. IntConsumer is a functional interface.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Zero Thread");

        Thread t2 = new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::println);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                /* InterruptedException is not an error. It is a signal that another thread is asking this thread to stop what it's doing.
                - This signal is delivered by someThread.interrupt();
                - When thread is sleeping, waiting, blocked, Java wakes the thread, clears the interrupted flag and throws InterruptedException.
                - Interrupt Flag is cleared when exception is thrown.
                - If we only do e.printStackTract():
                    - The interrupt signal is lost (No one upstream knows the thread was interrupted)
                    - The thread continues executing as if nothing happened.
                    - Methods that catch this Interrupt Exception are expecter to either stop execution or restore the interrupt
                - Thread.currentThread().interrupt() re-sets the interrupt flag so the interruption signal is preserved and higher level code can detect it.
                - You do not call Thread.currentThread().interrupt() to check if interrupt happended. You call it to restore / signal that interrupt has already happened.
                */
            }
        }, "Even Thread");

        Thread t3 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::println);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Odd Thread");

        t1.start();
        t2.start();
        t3.start();
    }
}
