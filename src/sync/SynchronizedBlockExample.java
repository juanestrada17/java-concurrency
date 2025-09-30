package sync;

class SyncBlock {
    private int count = 0; 
    // Create the lock as an object. 
    private final Object lock = new Object();

    public void increment(){
        synchronized (lock) {
            count++;
        }
    }

    public int getCount(){
        synchronized (lock) {
            return count;
        }
    }
}

public class SynchronizedBlockExample{
    // Interrupted exception will interrupt a thread that is currently blocked. 
    public static void main(String[] args) throws InterruptedException {
        SyncBlock counter = new SyncBlock();
        Runnable task = () -> {
            for(int i = 0; i < 1000; i ++){
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join(); // join waits 
        thread2.join();
        System.out.println("Final counter: " + counter.getCount());
    }
}
