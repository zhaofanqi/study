package juc; /**
 * ClassName ReadWriteLockDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/26 21:54
 * @Description: 该类主要用于 测试 读写锁的并发性
 * 写锁：  原子+独占   整个过程必须是完整的统一体，不允许被打断
 * <p>
 * 多个线程可以同时读取一个资源类，但是当有有一个线程写该资源类的时候，是不允许被其他资源读取的
 * 读 可以并发
 * 可  读+写 、写+读、写+写 是不允许并发的
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程操作资源类，mycache就是模拟的资源类，正常的缓存是有三个方法： 写入，读取，清空，而且分布式缓存底层都是map及其接口
 */

class MyCache {
    volatile Map<String, Object> map = new HashMap<String, Object>();

    ReadWriteLock rwl = new ReentrantReadWriteLock();


//      代码格式化  option + command +L

    public void put(String key, Object value) {

        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写入 ：" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成 ：");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rwl.writeLock().unlock();
    }


    public void get(Object object) {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取 ：");
            TimeUnit.MILLISECONDS.sleep(400);
            Object result = map.get(object);
            System.out.println(Thread.currentThread().getName() + "读取完成 ：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rwl.readLock().unlock();
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }


    }
}
