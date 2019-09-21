package juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ClassName BlockQueue
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/27 19:09
 * @Description: 该类主要用于研究阻塞队列
 * <p>
 * //总结：
 * //  方法类型    抛出异常        返回特殊值       阻塞          超时
 * //    插入       add           offer          put         offer(e,time,unit)
 * //    取出       remove        poll           take         poll(time,unit)
 * //    检查       element       peek           不可用         不可用
 * <p>
 * // 抛出异常： 队列满了添加时 IllegalStateException: Queue full/  队列空取元素 ：NoSuchElementException
 * // 特殊值 ： 成功true 失败false  不存在 null
 * // 阻塞： put 不成功会一直尝试put ,take不成功会一直尝试take
 * // 超时：当阻塞队列满时，队列会阻塞生产者一段时间，超过限时后，生产者线程会退出
 */
public class BlockQueue {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(4);

        //队列主要有三种方法： 增加元素，取出元素，检查元素

        bq.offer("a",2L, TimeUnit.SECONDS);
        bq.offer("a",2L, TimeUnit.SECONDS);
        bq.offer("a",2L, TimeUnit.SECONDS);
        bq.offer("a",2L, TimeUnit.SECONDS);
        System.out.println("=====尝试放入第五个=========");
        bq.offer("a",2L, TimeUnit.SECONDS);
        System.out.println("-----尝试结束,此时队列空余： ------------"+bq.remainingCapacity());
        System.out.println(bq.poll(1, TimeUnit.SECONDS));
        System.out.println(bq.poll(1, TimeUnit.SECONDS));
        System.out.println(bq.poll(1, TimeUnit.SECONDS));
        System.out.println(bq.poll(1, TimeUnit.SECONDS));
        System.out.println("尝试取出第五个");
        System.out.println(bq.poll(1, TimeUnit.SECONDS));
        System.out.println("尝试取出第五个结束");

//put  take程序会一直运行不退出
//        bq.put("a");
//        bq.put("a");
//        bq.put("a");
//        bq.put("a");
//        //bq.put("a");
//        System.out.println(bq.take());
//        System.out.println(bq.take());
//        System.out.println(bq.take());
//        System.out.println(bq.take());
//        System.out.println(bq.take());

        //增加元素,并且查看报错信息  IllegalStateException: Queue full
//        for (int i = 10; i <= 12; i++) {
//            System.out.println("this is " + i + "\t" + bq.add(String.valueOf(i)));
//        }
//        System.out.println(bq.element());//查看队列第一个元素
//
//        System.out.println(bq.remainingCapacity());//查看队列中有多少剩余容量
//        //取出元素的报错信息 NoSuchElementException
//        System.out.println(bq.remove());//取出队列第一个元素
//
//        System.out.println(bq.element());//查看队列第一个元素
        //bq.clear();//清空队列
//        System.out.println(bq.remainingCapacity());
//        System.out.println(bq.contains("11"));
//        System.out.println(bq.offer(String.valueOf(13)));//offer 只要不超过队列的长度  会将指定元素移动到队列的第一位，
//        System.out.println(bq.element());//取出队列第一个元素


    }
}

