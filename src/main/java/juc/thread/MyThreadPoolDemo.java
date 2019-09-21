package juc.thread;

import java.util.concurrent.*;

/**
 * ClassName MyThreadPoolDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/28 20:41
 * @Description: 线程池实现 多线程  关键类是 ThreadPoolExcutor,实际工作中，需要使用自定义的
 */
public class MyThreadPoolDemo {
    //手写一个线程池 使用 ThreadPoolExecutor

    public static void main(String[] args) {

        MyThreadPoolDemo myThreadPoolDemo = new MyThreadPoolDemo();
        ThreadPoolExecutor thread_1 = myThreadPoolDemo.threadPoolExecutor();
        try {
            for (int i = 1; i <= 10; i++) {
                thread_1.execute(() -> {
                   try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println("****" + Thread.currentThread().getName() + "****");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thread_1.shutdown();
        }

        //usual_threadPoolTest();


    }

    public ThreadPoolExecutor threadPoolExecutor() {

        ThreadPoolExecutor thread_1 = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
//         new ThreadPoolExecutor.CallerRunsPolicy());//哪个线程调用的该方法，让哪个线程去执行（比如 A 通过 C 知道 B 可以修车，B 满了 让 A 去找C 修车）
//         new ThreadPoolExecutor.DiscardOldestPolicy());
         new ThreadPoolExecutor.DiscardPolicy());

        return thread_1;
    }

    private static void usual_threadPoolTest() {
        //Executor Executors 是辅助工具类
        ExecutorService pool_1 = Executors.newFixedThreadPool(5);//创建固定数量的线程池
        ExecutorService pool_2 = Executors.newSingleThreadExecutor();//一个线程池仅仅一个线程
        ExecutorService pool_3 = Executors.newCachedThreadPool();//一个线程池可扩展线程个数

        //线程的关键是 ： 关闭   开启
        try {
            for (int i = 1; i <= 6; i++) {
                //pool_1.execute(() -> {
                //pool_2.execute(() -> {
                pool_3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "执行");
//                    try {
//                       // TimeUnit.MILLISECONDS.sleep(2000);
//                        System.out.println(Thread.currentThread().getName() + "执行");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // pool_1.shutdown();
            //pool_2.shutdown();
            pool_3.shutdown();
        }
    }
}
