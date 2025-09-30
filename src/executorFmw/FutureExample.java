package executorFmw;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService  executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Task Completed!";
        };

        // Future represents the result of an async operation
        // Executor will return a Future object - a placeolder for the result
        // future.get() blocks and retrieves the result once the task is complete
        // It allows use the main thread to keep working until it gets the result. 
        Future<String> future = executor.submit(task); 
        System.out.println("Doing other work... ");
        String result =  future.get();
        System.out.println(result);
        executor.shutdown();
    }
    
}
