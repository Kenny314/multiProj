package org.kenny.multi;

public class ThreadBaseDemo {
    public static void main(String[] args) {
        Integer i = 0;

        //Monitor 管程
        Object o = new Object();
        new Thread(()->{
            int c = 0;
            while (c<100){
                synchronized (i){
                    System.out.println("====="+i);
                }
                c++;
            }

        },"t2").start();

        Thread t1 = new Thread(()->{
            System.out.println("tttttttt");
            System.out.println("*****"+i);
        },"t1");
        System.out.println(t1.isDaemon());
        t1.start();




    }
}
