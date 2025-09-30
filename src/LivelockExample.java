import java.util.Random;

public class LivelockExample {
    static class Person {
        private String name; 
        private boolean movedAside = false; 
        private Random random = new Random();

        Person(String name){
            this.name = name;
        }

        public boolean moveAside(Person other){
            if (other.movedAside){
                System.out.println(name + " passes as " + other.name + " moved aside");
                return true; // can pass
            }
            System.out.println(name + " moves aside");
            movedAside = true; 
            try {Thread.sleep(random.nextInt(100));} catch (InterruptedException e){}
            return false;
        }
    }

    public static void main(String[] args) {
        Person alice  = new Person("Alice");
        Person bob = new Person("Bob");

        Thread t1 = new Thread(()-> {
            while(!alice.moveAside(bob)){
                alice.movedAside = false;
                try{ Thread.sleep(100);} catch(Exception e){}
            }
        });
        
        Thread t2 = new Thread(()-> {
            while(!bob.moveAside(alice)){
                bob.movedAside = false;
                try{ Thread.sleep(100);} catch(Exception e){}
            }
        });
        t1.start();
        t2.start();   
    } 
}
