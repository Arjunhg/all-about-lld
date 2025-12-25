package H_Concurrency.A_Thread_Runnable_Callable.B_Runnable_Interface;

public class RunnableExample {
    public static void main(String[] args) {
        
        MyRunnable myRunnable = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        /* Can also do:

        Thread t1 = new Thread(() -> {
            for(int i=0; i<5; i++){
                System.out.println("Runnable " + Thread.currentThread().threadId() + " is running: " + i); 
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Runnable Example interrupted");
                    e.printStackTrace();
                }
            }
        });
        
        */

        t1.start();
        t2.start();
    }
}
