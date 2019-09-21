package juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * ClassName DeadLockDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 11:48
 * @Description: 演示死锁： 2个甚至多个线程，持有资源，同时还想获取到 其他线程拥有的资源
 */


class DeadLockData {
    private String str_1;
    private String str_2;

    public DeadLockData(String str_1, String str_2) {
        this.str_1 = str_1;
        this.str_2 = str_2;
    }

    public void lock_1() {
        synchronized (str_1) {
            System.out.println(Thread.currentThread().getName() + "持有资源" + str_1);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //不这样的话，如何模拟出拥有资源1 还想拥有资源2 呢
            synchronized (str_2){
                System.out.println(Thread.currentThread().getName() + "想要持有资源" + str_2);
            }
        }
    }

    public void lock_2() {
        synchronized (str_2) {
            System.out.println(Thread.currentThread().getName() + "持有资源" + str_2);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (str_1){
                System.out.println(Thread.currentThread().getName()+"尝试获取资源"+str_1);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String str1 = "xxx";
        String str2 = "yyy";
        DeadLockData deadLockDemo = new DeadLockData(str1,str2);

        new Thread(()->{
            deadLockDemo.lock_1();
            deadLockDemo.lock_2();
        },"AAA").start();

        new Thread(()->{
            deadLockDemo.lock_2();
            deadLockDemo.lock_1();
        },"BBB").start();
    }

}
