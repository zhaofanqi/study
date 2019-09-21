package reference;

import java.lang.ref.SoftReference;

/**
 * ClassName SoftReferenct
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 18:39
 * @Description: 内存充足 软引用对象不被回收， 内存不足时软引用对象 会被回收
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        //测试软引用在内存充足是是否被回收
        SoftReferenceDemo softReferenceDemo = new SoftReferenceDemo();
        softReferenceDemo.memoryEnough();
//        softReferenceDemo.memoryNotEnough();


    }


    public void memoryEnough() {
        Object o = new Object();
        //创建一个软链接
        SoftReference softReference = new SoftReference(o);

        System.out.println(o);
        System.out.println(softReference.get());
        System.out.println("==================");
        o=null;
        System.gc();

        System.out.println(o);
        System.out.println(softReference.get());
    }
    public void memoryNotEnough() {
        Object o = new Object();
        //创建一个软链接
        SoftReference softReference = new SoftReference(o);

        System.out.println(o);
        System.out.println(softReference.get());
        System.out.println("==================");
        o=null;
        //调小堆内存的大小为 5m,同时创建一个数组大小为6m的。看一下软链接是否被回收
        //  -Xms5m  -Xmx5m -XX:+PrintGCDetails
        try{
            byte[] bytes = new byte[6 * 1024 * 1024];
        }catch (Exception e){
        //}catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println("=====堆内存空间不足测试======");
            System.out.println(o);
            System.out.println(softReference.get());
        }
    }
}