package syncAids;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // Initial count, 3 events before it can proceed
        CountDownLatch latch = new CountDownLatch(3);

        Runnable service = () ->{
            try {
                System.out.println(Thread.currentThread().getName() + " starting...");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " completed.");
                // each call decreases the count by 1. 
                latch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(service, "Database").start();
        new Thread(service, "Cache").start();
        new Thread(service, "Logger").start();

        System.out.println("Waiting for services...");
        // Main thread waits for the three services to complete. They are blocked until it happens. 
        latch.await();
        System.out.println("All services up. Starting application");
    }
}
