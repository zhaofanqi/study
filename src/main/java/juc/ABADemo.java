package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ClassName ABADemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/23 15:33
 * @Description:
 */



public class ABADemo {

    /*
    ABA 问题： CAS中存在的。由于cas 仅仅比较当前值与主内存中某一时刻值之间的差异，而忽略了主内存中的变量值是否发生过变化
        解决： 原子应用 + 时间戳（版本值）
            cas比较时，不仅仅比较值，同时比较版本值
     */

    /*
    第一部分： ABA问题产生
     */
    public static void main(String[] args) {
        AtomicReference<Integer> integerAtomicReference = new AtomicReference<>(100);
        System.out.println("============ ABA 问题的产生 ===============");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(integerAtomicReference.get()+" 这是初始值");
                integerAtomicReference.compareAndSet(100,101);
                System.out.println(integerAtomicReference.get()+" 这是当前值");
                integerAtomicReference.compareAndSet(101,100);
                System.out.println(integerAtomicReference.get()+" 这是当前值");
            }
        },"t1").start();


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integerAtomicReference.compareAndSet(100,2019);
                System.out.println(integerAtomicReference.get()+" 这是当前值");
            }
        },"t2").start();




        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(100, 1);
        int stamp = integerAtomicStampedReference.getStamp();
        new Thread(()->{
            try {

//                保证上面t1 ,t2的实验完成
                TimeUnit.SECONDS.sleep(3);
                System.out.println("==========aba 解决==============");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            integerAtomicStampedReference.compareAndSet(100,101,1,stamp+1);
            integerAtomicStampedReference.compareAndSet(101,100,integerAtomicStampedReference.getStamp(),stamp+1);
            System.out.println("当前值 : "+integerAtomicStampedReference.getReference()+"当前版本 ：" +integerAtomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            try {
//                保证t3 执行完成
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(integerAtomicStampedReference.compareAndSet(100, 209, 1, stamp + 1));
            System.out.println("当前值 : "+integerAtomicStampedReference.getReference()+"当前版本 ：" +integerAtomicStampedReference.getStamp());
        },"t4").start();
    }







}
