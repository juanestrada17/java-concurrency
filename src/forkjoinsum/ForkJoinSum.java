package forkjoinsum;

import java.util.concurrent.*;

public class ForkJoinSum extends RecursiveTask<Long> {
    private final int[] numbers;
    private final int start, end;
    private static final int THRESHOLD = 10;

    public ForkJoinSum(int[] numbers, int start, int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start <= THRESHOLD){
            long sum = 0;
            for(int i = start; i < end; i++){
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2; 
            ForkJoinSum left = new ForkJoinSum(numbers, start, mid);
            ForkJoinSum right = new ForkJoinSum(numbers, mid, end);
            // Fork one, compute one pattern: computer one action while the other ones run. 
            left.fork(); // executes asynchronously
            long rightResult = right.compute();
            long leftResult = left.join(); // waits for the left task to finish and gets its result
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[100];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = i +1;
        }
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long sum = pool.invoke(new ForkJoinSum(numbers, 0, numbers.length));
        System.out.println("Sum: " + sum);
    }
    
}
