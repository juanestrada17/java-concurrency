package syncAids;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample{
    public static void main(String[] args) {
        // Second parameter is the barrier action
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All tasks completed Phase"));

        Runnable task = ()->{
            try {
                System.out.println(Thread.currentThread().getName() + " workingmeasuringPoint() working...");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " reached barrier.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException ex) {
            }
        };

        Thread t1 = new Thread(task, "Worker1");
        Thread t2 = new Thread(task, "Worker2");
        Thread t3 = new Thread(task, "Worker3");

        t1.start();
        t2.start();
        t3.start();
    }
}