package H_Concurrency.A_Thread_Runnable_Callable.A_Thread_Class;

class MyThread extends Thread {
    @Override
    public void run(){ // Don't use throws clause here as the parent method in Thread class doesn't have it
        for(int i=0; i<5; i++){
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running: " + i); 
            try{
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}