public class DeadlockExample {
    public static void main(String[] args) {
        String resource1 = "Resource1"; 
        String resource2 = "Resource2"; 

        Thread t1 = new Thread(()-> {
            synchronized (resource1) {
                System.out.println("T1 locked resource1");
                try { Thread.sleep(100);} catch(Exception e){}
                synchronized (resource2) {
                    System.out.println("T1 locked resource2");
                }     
            }
        });

        Thread t2 = new Thread(()-> {
            synchronized (resource2) {
                System.out.println("T2 locked resource2");
                try { Thread.sleep(100);} catch(Exception e){}
                synchronized (resource1) {
                    System.out.println("T2 locked resource1");
                }     
            }
        });

        t1.start();
        t2.start();
    }
    
}
