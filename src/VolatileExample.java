public class VolatileExample {
    // Volatile keyword makes sure the write to flag is visible to the reader thread
    private volatile boolean flag = false;

    public void writer(){
        flag = true; // Visible to all threads
    }

    public void reader(){
        if(flag){
            System.out.println("Flag is true");
        }
    }

    public static void main(String[]args){
        VolatileExample example = new VolatileExample();
        Thread writer  = new Thread(() -> example.writer());
        Thread reader = new Thread(()-> example.reader());
        writer.start();
        reader.start();
    }
}
