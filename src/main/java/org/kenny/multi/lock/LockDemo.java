package org.kenny.multi.lock;

import java.util.concurrent.TimeUnit;

/**
 * 1.打印优先顺序
 * 2.sendEmail 睡眠3秒钟，打印顺心
 * 3.增加普通hello方法，让B线程调用，snedemail 睡眠2秒打印顺序
 * 4.两部手机，分别调用不同的方法，email睡眠2秒，是打印phone1，还是phone2，
 * 5.改为静态同步方法，1部手机
 * 6.改为静态同步方法，2部手机
 * 7.1部手机，1个静态方法，一个普通方法
 * 8.2部手机，1个静态方法，一个普通方法
 *
 *
 * 1、2 两锁，一个类中有多个synchronized 方法，某一个时刻，只要有一个线程可以访问
 * 有 syn 标识的方法，其他线程只能等待。这表明：既标识syn的方法都不能访问。
 *
 * 3锁，hello只是普通方法，那么可以先访问hello方法
 *
 * 4锁，两个phone的对象，并没有争抢syn对象，锁不一样，情况改变
 *
 * 5 - 6 两锁，情况一样，static 是类方法，所以，线程要等待
 *
 *
 */
public class LockDemo {

    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        Thread t1 = new Thread(
                ()-> {
                    phone1.sendEmail();
                },"A"
        );
        t1.start();

        Thread t2 = new Thread(
                ()-> {
                    phone2.sendSMS();
//                    phone.hello();
                },"B"

        );
        t2.start();

    }
}

class Phone{
    public static synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===== Send Email");
    }

    public synchronized void sendSMS(){
        System.out.println("===== send SMS");
    }

    public void hello(){
        System.out.println("------ hello ");
    }
}
