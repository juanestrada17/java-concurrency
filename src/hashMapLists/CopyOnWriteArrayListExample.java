package hashMapLists;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(100);
                list.add("C");
                System.out.println("Added C");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread reader = new Thread(() -> {
            for(String item : list){
                try{
                    Thread.sleep(100);
                    System.out.println("Read: " + item);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });

        writer.start();
        // The reader only sees "A" and "B" despite writer adding "C"
        reader.start();
    }    
}
