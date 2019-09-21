package juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * ClassName ArrayDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/23 16:54
 * @Description: 集合不安全类 ArrayList , HashSet ,HashMap 都是线程不安全的，HashSet的底层实现还是HashMap.
 *          但是HashSet.add时仅仅需要增加一个value即可，是因为HashSet.add 中的value都是一个虚拟值，传入的值被作为k
 */
public class ArrayDemoUnSafe {
    public static void main(String[] args) {

//        List<String> list =  Collections.synchronizedList(new ArrayList<>());
//       List<String> list =  new ArrayList<>();

        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
//        new HashSet<>();
//        Collections.synchronizedMap(new HashSet<>());
//        new CopyOnWriteArraySet<>();
new HashMap<>();
new ConcurrentHashMap<>();
        //ArrayList 是集合不安全类，因为它的add()没有加锁
        //Collection 是接口主要实现为 set list
        //Collections 是类  工具接口 一般都会有对应的辅助工具类。Collections就是Collection 的辅助工具类

/*
出现问题解决步骤：
1、故障现象：java.util.ConcurrentModificationException
3、导致原因：
   并发争抢修改导致
 public boolean add(E e) {
   final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray(); 复制资源
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);修改资源
            return true;
        } finally {
            lock.unlock();
        }
     }
2、解决方案：
  2.1 是用vector  new Vector<>(); 该类是线程安全的
  2.2 用 安全类包装非安全的 Collections.synchronizedList();
  2.3 使用 new CopyOnWriteArrayList<>(); 俗称写时复制，一种读写分离的思想。
        一份资源，写的时候单独copy一份进行修改，原有的一份对外提供使用，当修改完以后，将原有的那份作废并将引用全都指向写完的最新资源上
4、建议优化:(如何二次避免该问题出现)

 */

/**
 * 写时复制：
 * CopyOnWrite 容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器object[]添加，而是先将容器object[]进行copy，复制出
 *      一个新的容器 object[] newElements,然后 新的容器 object[] newElements 里添加元素，添加完元素以后，再将原来容器的引用指向新的容器 setArray(newElements);
 *      这样做的好处：可以对 CopyOnWrite 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以 CopyOnWrite 容器也是一种读写分离的思想，读写不同的容器
 */

//        for (int i = 0; i <5 ; i++) {
//            new Thread(()->{
////                System.out.println(Thread.currentThread().getName() +list);
//                list.add(UUID.randomUUID().toString().substring(0, 8));
////                System.out.println(Thread.currentThread().getName() +list);
//
//            },String.valueOf(i)).start();
//            System.out.println("i  is  ： "+i);
//            System.out.println(list);
//        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0, 8));
//                    System.out.println("i  is  ： "+Thread.currentThread().getName());
//                    System.out.println(list);
                }
            },String.valueOf(i)).start();
//            System.out.println("i  is  ： "+i);
//            System.out.println(list);
        }
        /*
        *
        * 这就是问题了啊！ 为什么在for循环外面使用输出list得到的list 结果就是空呢？难道写的时候不可见？
        * 不是写时不可见，而是main线程执行的时候，其他线程可能还没写入
         */
//        while (Thread.activeCount()>2){
//            Thread.yield();
//        }
        System.out.println(list);
        System.out.println("running .... "+Thread.activeCount());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("running .... "+Thread.activeCount());
        System.out.println(list);


    }
}
