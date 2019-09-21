package juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * ClassName ThreadDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/28 18:41
 * @Description:  多线程的实现方式有 实现 Runnable 常见
 *                                 继承  Thread()  少见
 *                                 实现 Callable 接口  最新常用
 *                                 使用线程池方式创建多线程 见案例：/Users/zhaofanqi/workspace/case1/src/main/java/juc/thread/MyThreadPoolDemo.java
 *                  AB： Callable 与 Runnable 对比 ：Callable 可以范型，带返回值以及异常信息（方便确认问题），实现方法不同
 * java 是高扩展的： 如何找到 Callable 和 Runnable 联系：
 *
 */
class MyThread00 implements Runnable{

    /**
     *  FutureTask --> RunnableFuture ---> Runnable
     *  FutureTask 的构造器中 使用了适配器，即构造器中有 Callable 接口
     *  从而 FutureTask 将 Runnable 与 Callable 关联 了起来
     */
    @Override
    public void run() {

    }
}
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("进入新建线程的构造器中");

       // TimeUnit.SECONDS.sleep(2);
        method_c();
        System.out.println("进入新建线程的构造器中 睡眠 2 秒 ");
        return 1024;

    }

    public void method_a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+" method_a sleep time 2 s ");

    }
    public void method_b() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+" method_b sleep time 2 s ");
    }
    public void method_c() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        System.out.println(Thread.currentThread().getName()+" method_c sleep time 10 s ");
    }
    public void method_d() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+" method_d sleep time 2 s ");
    }
}
public class ThreadDemo {
    //假设 原来的主线程为 ： a()-->b()-->c()-->d() 串形执行，而其中的c()方法执行时间很久，需要新建线程完成


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        MyThread myThread = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread);//将 Callable 与 FutureTask 建立联系
        // 将 FutureTask 与 Runnable 接口建立联系
        new Thread(futureTask,"AAAA").start();//只有执行了 .start() 方法，才会真正的去执行 MyThread 中的 call()


        System.out.println("======任务开始=========");
        myThread.method_a();
        myThread.method_b();
       // myThread.method_c();
        myThread.method_d();
        System.out.println(futureTask.get());//新建线程的返回值最好是放到最后获取，这样main线程不需要一直等待，或者
                                            // 可以使用 futureTask.isDone() 判断，这样新的线程中一旦执行完成就会返回结果
        System.out.println("=======任务结束======");

    }
}
