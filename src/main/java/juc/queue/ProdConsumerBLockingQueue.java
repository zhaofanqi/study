package juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName ProdConsumerBLockingQueue
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/28 17:10
 * @Description: 模拟：线程通信 之生产者消费者 阻塞队列
 * 需要实现效果： 生产一个，消费一个，外层其他线程可以控制停止，开始
 */
//资源类
class MyResource {
    private volatile Boolean FLAG = true;//控制是否生产消费（生产消费是配对的，从而判断干活的标识应一致）
    BlockingQueue<String> blockingQueue = null;//不知道调用该资源类用的是哪种阻塞队列，最好是用接口

    private AtomicInteger atomicInteger = new AtomicInteger();//默认初始值为 0

    public MyResource(BlockingQueue<String> blockingQueue) {//构造方法根据指定阻塞队列生产不同的队列

        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName() + "\t 为输出的阻塞队列类型");
    }

    //判断 干活  唤醒
    public void myPord() throws Exception {
        String data = null;
        Boolean resValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            resValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(1);
            if (resValue) {
                System.out.println(Thread.currentThread().getName() + "存入一个元素" + data + " 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "存入一个元素" + data + " 失败");
            }

        }
        //要是被结束的话：
        System.out.println("生产线程被叫停");
    }

    public void myConsumer() throws Exception {
        String resCon;
        while (FLAG) {
            resCon = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == resCon || resCon.equalsIgnoreCase("")) {//判断输出字符串是否为空
                System.out.println(Thread.currentThread().getName() + "获取到队列中的元素 " + resCon + " 为 失败");
                FLAG=false;//超过取出时间，或者就是没有，避免死循环还需要有return,如果超过2秒没有生产出资源也会退出
                return;
            }
            System.out.println(Thread.currentThread().getName() + "获取到队列中的元素 " + resCon + " 为 成功");
        }
        //要是被结束的话：
        System.out.println("消费线程被叫停");

    }

    public void stop(){
        this.FLAG=false;
        System.out.println(Thread.currentThread().getName()+"结束生产与消费的模式");
    }

}

public class ProdConsumerBLockingQueue {
    public static void main(String[] args) {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));//线程操作资源类的时候就将阻塞队列确定好
        new Thread(()->{
            try {
                myResource.myPord();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AAAA").start();

        new Thread(()->{
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BBBB").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        myResource.stop();

    }
}
