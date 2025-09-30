
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        Runnable task = () ->{
            for(int i = 0; i < 100; i++){
                map.compute("key", (k, v) -> v == null ? 1 : v + 1);
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Final count: " + map.get("key")); // 200 since task was performed by both threads
    }
}
