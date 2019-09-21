package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName VolatileDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/4/21 15:13
 * @Description:
 */

class MyData{
     //定义一个成员变量
    volatile int  num = 0;

    public void addTo60() {
       this.num=60;
    }
    //由于volatile 不保证原子性，多线程调用该变量的时候，无法保证结果按照预期出现
    public void addPlusPlus(){
        num++;
    }
    //option + enter 可以帮助自动引入 类
    AtomicInteger atomicInteger=new AtomicInteger();
    public void atoMyadd(){
        atomicInteger.getAndAdd(1);
    }





}
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();


        //seeOkByVolatile();
        for (int i = 1; i <=20 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <=1000 ; j++) {
                        myData.addPlusPlus();
                        myData.atoMyadd();
                        //System.out.println(Thread.currentThread().getName()+": "+myData.num);
                    }
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("不保证唯一性： "+myData.num);
        System.out.println("验证是否保证唯一性： "+myData.atomicInteger);
    }



    //该部分代码用于验证volatile 关键字的可见性
    //idea中将代码抽取为方法快捷键为 ctrl + alt +M   mac 下为： option +command +M
    private static void seeOkByVolatile() {
        MyData myData=new MyData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("coming in thread_01");
               //必须先让线程空闲一会保证多个线程读到的都是主内存中的数据
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //因为线程都是在自己的工作内存中修改变量的
                myData.addTo60();
                System.out.println(" num is :"+myData.num+" ;  ");
            }
        },"thread_01").start();
        //验证其他线程是否知道主内存中的变量是否知道
        // 当添加volatile 关键字以后，main线程知道了变量的变化：这也就是可见性（一个线程修改了主内存中的变量内容会及时通知其他线程变量发生变化）
        while (myData.num==0){

        }
        System.out.println("mission is over");
    }


}
