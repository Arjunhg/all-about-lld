package H_Concurrency.A_Thread_Runnable_Callable.B_Runnable_Interface;

public class MyRunnable implements Runnable {
    
    @Override
    public void run(){ // Don't use throws clause here as the parent method in Runnable interface doesn't have it
        for(int i=0; i<5; i++){
            System.out.println("Runnable " + Thread.currentThread().threadId() + " is running: " + i); 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Runnable Example interrupted");
                e.printStackTrace();
            }
        }
    }
}
