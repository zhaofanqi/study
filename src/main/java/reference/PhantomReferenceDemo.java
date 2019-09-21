package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * ClassName PhantomReferenceDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 20:28
 * @Description: 虚引用 ：跟踪对象被垃圾回收的状态， 配合引用队列使用 （当引用对象被回收时，将引用的一些信息保存在引用队列中）
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference<Object> weakReference = new WeakReference<>(o, referenceQueue);

        System.out.println(o);
        System.out.println("referenceQueue: "+ referenceQueue.poll());
        System.out.println("weakReference: "+ weakReference.get());
        o=null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);//保证GC 完成

        System.out.println("================");
        System.out.println(o);
        System.out.println(" after GC ,referenceQueue: "+ referenceQueue.poll());
        System.out.println(" after GC ,weakReference: "+ weakReference.get());

    }

}
