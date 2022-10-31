package org.kenny.multi;

public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"---"+Thread.currentThread().isDaemon());
        },"ttt");
        // This method must be invoked before the thread is started.
        t1.setDaemon(true);
        t1.start();
    }

}
