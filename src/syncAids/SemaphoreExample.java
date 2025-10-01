package syncAids;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    static class ResourcePool{
        // ONLY two threads can access the resource at a time. 
        private final Semaphore semaphore = new Semaphore(2);
        
        public void accessResource(){
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " accessing resource");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing resource");
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        ResourcePool pool = new ResourcePool();
        Runnable task = () -> pool.accessResource();
        for(int i = 1; i <= 5; i++){
            new Thread(task, "Thread-" + i).start();
        }
    }
    
}
