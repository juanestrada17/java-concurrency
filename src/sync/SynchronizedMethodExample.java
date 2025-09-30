package sync;

class Counter {
    private int count = 0; 

    public synchronized void increment(){
        count++;
    }

    public synchronized int getCount(){
        return count;
    }

}

public class SynchronizedMethodExample { 
    public static void main(String[] args) throws InterruptedException{
        Counter counter =  new Counter();
        // Expects 2000 
        Runnable task = () -> {
            for(int i = 0; i < 1000 ; i++){
                counter.increment();
            }
        };
        
        // No runnable option
        // Runnable task = new Runnable() {
        // @Override
        // public void run() {
        //     for (int i = 0; i < 1000; i++) {
        //         counter.increment();
        //     }
        // }
        // };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        // Needs InterruptedException
        thread1.join(); 
        thread2.join();
        System.out.println("Final counter: " + counter.getCount());
    }
}