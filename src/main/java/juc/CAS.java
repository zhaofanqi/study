package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName CAS
 *
 * @Auther: 赵繁旗
 * @Date: 2019/4/28 13:18
 * @Description:
 */
public class CAS {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data :"+atomicInteger+" \t");
    }


}
