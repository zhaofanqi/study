package juc.queue;


import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * ClassName SynchronousQueueDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/27 21:41
 * @Description:  阻塞队列无容量，put一个元素后，必须是take ,否则无法继续put
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {


        BlockingQueue sq = new SynchronousQueue();
        System.out.println("队列容量大小"+sq.remainingCapacity());
        //既然是 只能put一个取出一个，不允许队列中出现大于一个的
         new Thread(()->{
             for (int i = 0; i <3 ; i++) {
                 try {
//                     System.out.println("======");
                     sq.put(i);
                     System.out.println(Thread.currentThread().getName()+"往队列中添加一个元素\t :"+i+"\t完成");
                     //TimeUnit.SECONDS.sleep(1);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         },"aaa").start();


        new Thread(()->{
            for (int i = 0; i <3 ; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+"从队列中取出一个元素： "+sq.take());
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
//         new Thread(()->{
//             long time_2;
//             try {
//                 time_2= new Date().getTime();
//                 System.out.println(Thread.currentThread().getName()+"\t从队列中取出元素:"+sq.take()+"时间为 ："+time_2);
//                 TimeUnit.SECONDS.sleep(2);
//                 time_2= new Date().getTime();
//                 System.out.println(Thread.currentThread().getName()+"\t从队列中取出元素:"+sq.take()+"时间为 ："+time_2);
//                 TimeUnit.SECONDS.sleep(2);
//                 time_2= new Date().getTime();
//                 System.out.println(Thread.currentThread().getName()+"\t从队列中取出元素:"+sq.take()+"时间为 ："+time_2);
//                 TimeUnit.SECONDS.sleep(2);
//                // System.out.println(sq.remainingCapacity());
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         },"BBB").start();


    }
}
