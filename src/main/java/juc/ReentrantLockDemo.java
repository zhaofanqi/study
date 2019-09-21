package juc; /**
 * ClassName ReentrantLockDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/24 19:15
 * @Description: 主要介绍 Sync ReentrantLock()
 *              该部分代码，主要演示可重入锁（递归锁）
 *               当同一个线程获取外层函数的锁时，当进入内层函数的时候会自动获取锁
 *               也就是同一个线程 可以进入到 任何一个它已经获取锁 所同步的代码块
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程操作资源类
 */
class Phone implements  Runnable{
    public synchronized  void get(){
        System.out.println(Thread.currentThread().getName()+"\t invoke get()");
        set();
    }

    public synchronized  void set() {
        //此处输出的线程名称一样,也就是说 同一个线程可以进入任何一个它已经拥有锁 所同步的代码块
        System.out.println(Thread.currentThread().getName()+"\t here is  set()");

    }

    @Override
    public void run() {
        LockDemo();
    }
    ReentrantLock lock = new ReentrantLock();
    public   void LockDemo() {

        lock.lock();
        //lock.lock(); lock 与unlock 的个数需要匹配，否则后面的无法执行
        System.out.println(Thread.currentThread().getName()+"\t invoke get()");
        LockDemo_2();
        lock.unlock();

    }

    public synchronized  void  LockDemo_2() {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"\t here is  inner method ()");
        lock.unlock();
    }
}
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.get();
        },"t1").start();

         new Thread(()->{
            phone.get();
        },"t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();

    }
}
