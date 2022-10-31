package org.kenny.multi.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

public class OnlineLikeDemo {
    private static int SIZE = 50;
    private static int LIKE_NUM = 1000000;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        LongAdder longAdder = new LongAdder();
        for(int i = 1;i <= 50;i++){
            new Thread(()->{
                for(int j = 1;j<=LIKE_NUM;j++){
                    longAdder.increment();
                }
                countDownLatch.countDown();
            },"Thread"+i).start();

        }
        countDownLatch.await();

        System.out.println(longAdder.sum());

    }
}
