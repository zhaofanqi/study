package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ClassName CyclicBarrierDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/27 17:30
 * @Description:  循环屏障：当一组线程到达屏障点时，直到最后一个线程到达，才会进入屏障，调用方法为 await()方法
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        // argum  : 线程数量，以及都到达后执行的操作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("所有的线程都已经到达屏障点，执行屏障点后续操作");
        });

        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                System.out.println("线程 ： "+Thread.currentThread().getName()+"正在执行");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
