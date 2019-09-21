package reference;

/**
 * ClassName StrongReference
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 18:36
 * @Description: 强引用案例 强引用不会被垃圾回收，即使是OOM也不会的 只有当强引用对象被设置为null
 */
public class StrongReference {

    public static void main(String[] args) {


        Object o = new Object();
        Object o_2=o;
        o=null;
        System.gc();
        System.out.println(o);
        System.out.println(o_2);
    }
}

