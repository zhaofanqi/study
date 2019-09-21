package juc.queue;

/**
 * ClassName ProdConsumerDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/27 22:42
 * @Description: 生产者消费者传统模式
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 高并发下：
 * 线程   操作（方法）    资源类
 * 判断   干活          唤醒
 * 严防高并发下的虚拟唤醒
 * 补充： 对于资源的判断不可以使用if判断！！要用while .否则当线程大于2个时候会出错
 */
//  进行+1 -1 操作
class ShareData {
    private int number = 0;
    //设计多个线程，所以需要有锁的
    Lock lock = new ReentrantLock();
    // 为什么要用Condition ,在Condition 类的源码中指出，用Lock代替Synchronized的地方，要用Condition代替原来的对象监控方法
    // 同时Condition.await方法是阻塞
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();

        try {
            while (number != 0) {
                //等待
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "the number is :" + number);
        //必须在解锁之前通知：Each thread must re-acquire the lock before it can return from {@code await}，
        //                      源码中要求必须在获取锁之前 从await()中返回
        condition.signalAll();
        lock.unlock();

    }


    public void decrement() {
        lock.lock();
        while (number == 0) {
            //等待
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + "the number is :" + number);
        condition.signalAll();
        lock.unlock();
    }


}

public class ProdConsumerDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.increment();
            }
        }, "AAA").start();


        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "BBB").start();



        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.increment();
            }
        }, "CCC").start();


        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "DDD").start();
    }

}
