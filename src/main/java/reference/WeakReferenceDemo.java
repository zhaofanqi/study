package reference;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * ClassName WeakReferenceDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 19:00
 * @Description: 弱引用对象，不管内存是否够不够，发生gc都进行回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());
        o=null;
        System.gc();
        //TimeUnit.SECONDS.sleep(1);
        //o=null;
        System.out.println("完成gc()");
        System.out.println(o);
        System.out.println(weakReference.get());


    }
}
