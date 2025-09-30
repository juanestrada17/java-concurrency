package executorFmw;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
         4, 
         60, TimeUnit.SECONDS, 
        new LinkedBlockingQueue<>(2), // WorkQueue, holds tasks when all threads are busy. 
        Executors.defaultThreadFactory(), // Customizations on thread creation. 
        new ThreadPoolExecutor.AbortPolicy() // Triggers if the queue and max threads are full
        );

        for (int i = 1; i <= 6; i++){
            final int taskId = i;
            executor.execute(()->{
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
    }
    
}
