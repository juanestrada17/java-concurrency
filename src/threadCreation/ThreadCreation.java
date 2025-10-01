package threadCreation;
public class ThreadCreation {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }
    
}

// Extend the thread class 
class MyThread extends Thread {
    @Override
    // Override the run method
    public void run(){
        for(int i = 1; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " printing: ");
            try{
                Thread.sleep(500); // delay
            } catch (InterruptedException e){
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}
