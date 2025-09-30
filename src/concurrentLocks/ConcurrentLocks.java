package concurrentLocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class LockExample {
    private int count = 0; 
    // ReentrantLock allows explicit locking and unlocking, ensureing thread safety / finally ensures lock is released even with
    // exception. 
    private final ReentrantLock lock = new ReentrantLock(); 
    
    public void increment() throws InterruptedException{
        boolean acquired = false; 
        // lock.lock();
        try {
            // Attempts to acquire a lock, waiting up to the given time before giving up. More common without a timeout
            acquired = lock.tryLock(2, TimeUnit.SECONDS);
            if (acquired) {
                count++;
            }  
        } catch(InterruptedException e){
            System.out.println(e);
        } finally {
            if(acquired){
                lock.unlock();
            }
        }
    }

    public int getCount(){
        lock.lock();
        try {
            return count;
        } finally{
            lock.unlock();
        }
    }
}

public class ConcurrentLocks{
    public static void main(String[] args) throws InterruptedException {
        LockExample counter = new LockExample();
        Runnable task = () -> {
            for(int i = 0; i < 1000 ; i ++){
                try {
                    counter.increment();
                } catch (InterruptedException ex) {
                }
            }
        };

        Thread thread1 =  new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Final Count: " + counter.getCount());


    }
}
