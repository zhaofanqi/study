package reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * ClassName WakeHashMapDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 19:22
 * @Description: wakeHashMap 的小案例
 * 缓存底层都是HashMap 那么对于需要回收的缓存如何存储呢？ 使用 wakeHashMap
 */
public class WakeHashMapDemo {
    public static void main(String[] args) {
        //myHashMap();
        myWeakHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<Integer,String>();
        Integer key=new Integer(2);
        String  value="WeakHashMap";
        map.put(key,value);
        System.out.println(map);

        key=0;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }

    private static void myHashMap() {
        Map<Integer, String> map = new HashMap<Integer,String>();
        Integer key=new Integer(1);
        String  value="hashMap";
        map.put(key,value);
        System.out.println(map);

        key=0;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());

    }
}
