package org.kenny.multi;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> completableFuture = new CompletableFuture<>();
//        asyncComWithPool();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println(Thread.currentThread().getName());
                int i = 0;
                while(i < 4){
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("loop =" + i);
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return ""+i;
            }
        },executorService);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println(Thread.currentThread().getName());
                int i = 5;
                while(i < 9){
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("loop =" + i);
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return ""+i;
            }
        },executorService);
        System.out.println(completableFuture.get());
        System.out.println(completableFuture2.get());
        executorService.shutdown();

    }

    private static void asyncComWithPool() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
                ()->{
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                },executorService
        );
        System.out.println(completableFuture.get());
        executorService.shutdown();
    }
}
