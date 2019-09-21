package oom;

import java.util.concurrent.TimeUnit;

/**
 * ClassName MyCreateNativeThread
 *
 * @Auther: 赵繁旗
 * @Date: 2019/7/8 14:23
 * @Description: 用于测试 创建本地线程数量超过限制
 *
 * 报错信息为:java.lang.OutOfMemoryError: unable to create new native thread
 * 报错原因： 应用创建太多的线程了，或者创建的线程超过了linux服务器的设置了
 * 解决措施：
 *  想办法降低应用程序创建线程的数量，分析应用是否需要创建这么多的线程，如果不是，调整程序
 *  对于有的应用，确实要创建很多线程 ，远超过 linux系统的1024 ，可以通过修改linux服务器的配置，扩大该值
 */
public class MyCreateNativeThread {


    public static void main(String[] args) {


        for (int i = 1; ; i++) {
            System.out.println("this is thread i: " + i + "start");
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "" + i).start();
        }
    }
}

