package juc;

import Bean.MyEnumDemo;


import java.util.concurrent.CountDownLatch;


public class CountDownLockDemo {

    public static void main(String[] args) {

        CountDownLatch cdl = new CountDownLatch(6);


        // 扩充使用枚举类，从而实现变量的全局替换
        // 此处线程名称处，用枚举来指定被灭国家与编号像对应
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                cdl.countDown();
            }, MyEnumDemo.foreach_country(i).getResValue()).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t 国统一天下");

    }
}
