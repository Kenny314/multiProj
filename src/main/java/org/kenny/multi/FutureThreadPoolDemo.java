package org.kenny.multi;

import java.util.concurrent.*;

/**
 * 1. futuretask.get() will block other thread
 * 2. if we don't want to waiting long time,
 *
 * CompletableFuture is better
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        singleTHread();
        long startTime = System.currentTimeMillis();
        FutureTask<String> futureTask1 = new FutureTask<>(
                ()->{
                    TimeUnit.MILLISECONDS.sleep(500);
                    return "task1 over";
                }
        );
        threadPool.submit(futureTask1);
//        System.out.println(futureTask1.get());
//        System.out.println(futureTask1.get(1000,TimeUnit.MILLISECONDS));

        FutureTask<String> futureTask2 = new FutureTask<>(
                ()->{
                    TimeUnit.MILLISECONDS.sleep(300);
                    return "task2 over";
                }
        );
        threadPool.submit(futureTask2);
//        System.out.println(futureTask2.get());
//        System.out.println(futureTask2.get(1000,TimeUnit.MILLISECONDS));
        TimeUnit.MILLISECONDS.sleep(1000);
        long endTime = System.currentTimeMillis();
        threadPool.shutdown();


        System.out.println("cost Time = "+(endTime - startTime));
    }


    /**
     * single thread
     */
    private static void singleTHread() {
        long startTime = System.currentTimeMillis();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
            TimeUnit.MILLISECONDS.sleep(1000);
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("cost Time = "+(endTime - startTime));
        System.out.println(Thread.currentThread().getName());
    }
}
