package example300;

/**
 * ClassName Case43
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/14 19:09
 * @Description: Singleton
 */
public class Case46 {
    public static void main(String[] args) {
        SingletonDemo instanceA = SingletonDemo.getInstance();
        SingletonDemo instanceB = SingletonDemo.getInstance();
        System.out.println("2个对象是否想等："+(instanceA==instanceB));

    }
}
class SingletonDemo{
    private static SingletonDemo single=new SingletonDemo();//创建了单例对象，此时为 恶汉式,加载类模版的时候，会加载静态属性

    private  SingletonDemo() {
        //此时为懒汉式,需要创建对象的时候，才被加载
       // single==new SingletonDemo();
    }


    public static  SingletonDemo getInstance(){
        return single;
    }

}