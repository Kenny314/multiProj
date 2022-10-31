package org.kenny.multi;

import java.util.concurrent.*;

public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //        comFuGetBlock();

        //加入线程池之后，我们的线程在线程池去执行，避免主线程结束后子线程也结束
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try{
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(
                    ()->{
                        System.out.println(Thread.currentThread().getName()+"come in --------");
                        int result = ThreadLocalRandom.current().nextInt(6);
                        try {
                            System.out.println("停留 ：" +result);
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        return result;
                    },executorService).whenComplete((v,e)->{
                if(e == null){
                    System.out.println("------- 计算完成：" + v);
                }
            }).exceptionally(e ->{
                e.printStackTrace();
                System.out.println(e.getCause() +"---" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName()+"--Main 去完成其他任务");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }



//        TimeUnit.SECONDS.sleep(9);

    }

    private static void comFuGetBlock() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"come in --------");
                    int result = ThreadLocalRandom.current().nextInt(5);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return result;
                }
        );
        System.out.println(Thread.currentThread().getName()+"come out --------");
        System.out.println(completableFuture.get());
    }
}
