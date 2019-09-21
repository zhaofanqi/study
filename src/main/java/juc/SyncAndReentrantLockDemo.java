package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName SyncAndReentrantLockDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/28 01:35
 * @Description: 用于实现 线程唤醒指定线程
 * Synchronized 无法精准唤醒指定线程，ReentrantLock 可以指定唤醒对应的线程
 * <p>
 * 多线程之间按顺序调用，实现 AA -> BB --> CC 三个线程启动，要求如下：
 * 线程 AA 打印 5次 ，线程 BB 打印 10 次 ，线程CC 打印 15 次
 * 来 10 轮
 */
class MyShareData {
    private String threadName = "A";//A->B->C

    //需要用到线程的精确唤醒 ：使用Lock的condition
    private Lock lock = new ReentrantLock();
    // condition 指定唤醒的线程，所以需要有三个
    private Condition condition_A = lock.newCondition();
    private Condition condition_B = lock.newCondition();
    private Condition condition_C = lock.newCondition();

    public void print_5() {
        lock.lock();
        try {
            while (!threadName.equals("A")) {
                condition_A.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            threadName = "B";
            condition_B.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print_10() {
        lock.lock();
        try {
            while (!threadName.equals("B")) {
                condition_B.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            threadName = "C";
            condition_C.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print_15() {
        lock.lock();
        try {
            while (!threadName.equals("C")) {
                condition_C.await();
            }
            for (int i = 10; i <= 25; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            threadName = "A";
            System.out.println("===================");
            condition_A.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {

        MyShareData myShareData = new MyShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myShareData.print_5();
            }

        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myShareData.print_10();
            }

        }, "BBB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myShareData.print_15();
            }

        }, "CCC").start();

    }
}
