public class ThreadCreationInterface {
    public static void main(String[] args) {
        // The thread object is created with the runnable instance. 
        Thread thread1 = new Thread(new MyThread());
        Thread thread2 =  new Thread(new MyThread());
        thread1.start();
        thread2.start();
    }
    
}

// Interface is preferable since with it we manage to keep task logic separate from thread creation
class MyThread implements Runnable{
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
