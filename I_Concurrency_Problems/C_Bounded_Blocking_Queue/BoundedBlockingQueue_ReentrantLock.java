package I_Concurrency_Problems.C_Bounded_Blocking_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue_ReentrantLock {
    
    private final int n;
    
    private final Queue<Integer> queue = new LinkedList<>(); //protect this
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition(); // Condition naming should be named after the state a thread is waiting for. Producer waits until the queue is not full 
    private final Condition notEmpty = lock.newCondition(); // Consumer waits until the queue is not empty
    

    public BoundedBlockingQueue_ReentrantLock(int capacity){
        this.n = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
      lock.lock();
      try{
        while(queue.size() == n){
          notFull.await();
        }
        queue.offer(element);
        notEmpty.signal();
      } finally {
        lock.unlock();
      }
    }
    
    public int dequeue() throws InterruptedException {
      lock.lock();
      try{
        while(queue.isEmpty()){
          notEmpty.await();
        }
        int element = queue.poll();
        notFull.signal();
        return element;
      } finally {
        lock.unlock();
      }
    }
    
    public int size() throws InterruptedException {
      lock.lock();
      try{
        return queue.size();
      }finally{
        lock.unlock();
      }
    }
}