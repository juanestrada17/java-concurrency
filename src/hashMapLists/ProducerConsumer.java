package hashMapLists;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    public static void main(String[] args) {
        // Producer Consumer pattern means adding items to queue, removing items from queue and BlockingQueue handling
        // Synchronization and coordination 
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        
        Runnable producer = () ->{
            try {
                for(int i= 0; i<= 5; i++){
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () ->{
            try {
                for(int i= 0; i<= 5; i++){
                    int  item = queue.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
    
}
