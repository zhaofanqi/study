package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName SpinLockDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/24 23:12
 * @Description: 此部分主要是自旋锁：线程获取锁失败不会进入阻塞状态，而是采取不断循环的方式直到获取锁，
 *          该种方式可以减少线程上下文的切换，缺点是 若一直无法获取到锁则会导致CPU 的消耗
 *      CAS 的实现中存在自旋锁，即使用do while 的方式，不断判断是否得到了锁，判断方式用的是CompareAndSet
 *      而CAS 的目的是保证原子性
 */
public class SpinLockDemo {

    //案例需要： 写一个原子引用中包装的是线程，那么自旋锁，锁的就是这个线程是否为null
    //因为在Java中引用变量初始值默认为null 的。所以判断条件为null

    /**
     * @param args
     * 期望第一个进来的得到锁，后续进来的得不到 类似于以前写的单例模式
     *          单例模式关键点： 私有的静态变量，私有的静态构造方法，公有的对外提供单例的方法，考虑到多线程，还需要加锁。当然可以利用jvm的特点
     *                                          直接让私有的静态变量初始化。
     */

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread "+thread.getName()+" coming in and invoked myLock()  ");
        /**
         * 此处的while就是类似于cas中的自旋锁了；第一个获取到资源的，对他进行判断，如果是空的就不执行while的等待逻辑了
         */
        while (!atomicReference.compareAndSet(null,thread)){

        }

    }

    public void unMyLock(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread "+thread.getName()+" get out and finish invoked  unMyLock()  ");
        while (atomicReference.compareAndSet(thread,null)){

        }

    }

    /**
     *
     * @param args
     *
     * 写代码思路： 多线程  ——————》  自旋锁 while不断判断 ---》就需要有使用资源的+释放资源的
     *          如此处用的是Thread是否为null ,同样的可以使用AtomicInteger的某个值进行判断如，➕1 ，➖1
     */
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        },"AAA").start();


        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("确保先进入的是AA 线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        },"BB").start();

    }
}
