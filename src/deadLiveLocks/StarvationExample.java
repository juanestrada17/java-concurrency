package deadLiveLocks;

import java.util.concurrent.locks.ReentrantLock;

public class StarvationExample {
    // unfair locks are better for performance
    private static final ReentrantLock lock = new ReentrantLock(); // unfair, prefers threads that are already locking
    // no starvation, predicatability
    private static final ReentrantLock nonStarvedLock = new ReentrantLock(true); // Prevents prioritization of thread locks.
    public static void main(String[] args) {
        Runnable task = () -> {
            while(true){
                if(lock.tryLock()){
                    try{
                        System.out.println(Thread.currentThread().getName() + " acquired lock");
                        Thread.sleep(100);
                    } catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        for(int i = 1; i <= 3; i++){
            Thread t = new Thread(task, "Thread-" + i);
            t.setPriority(i * 2); // Varying priorities
            t.start();
        }
    }
    
}
