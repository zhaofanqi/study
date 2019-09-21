package juc;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ClassName SemaphoreDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/27 18:10
 * @Description:  多个线程抢占多个资源 具有伸缩性
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        List list=null;

        //假如是停车问题，6辆车，抢占3个停车位
        //argus 第一个：资源数量 ，第二个 是否是公平锁，默认是非公平
        Semaphore semaphore = new Semaphore(3);


        for (int i = 1; i <=12 ; i++) {
            new Thread(()->{
                //抢占一个资源
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //资源释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
        //main方法不会被阻塞
        System.out.println("main方法是否被阻塞");
    }
}
