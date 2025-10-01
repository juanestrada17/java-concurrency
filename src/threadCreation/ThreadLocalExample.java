package threadCreation;

// Each thread has their own context isolated from the others. 
public class ThreadLocalExample {
    private static final ThreadLocal<String> userContext = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable task = () ->{
            userContext.set(Thread.currentThread().getName() + "-User");
            System.out.println(Thread.currentThread().getName() + " context: " + userContext.get());
        };

        Thread t1 = new Thread(task, "User1");
        Thread t2 = new Thread(task, "User2");
        t1.start();
        t2.start();
    }
    
}
