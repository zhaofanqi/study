package oom;

/**
 * ClassName MyMetaspace
 *
 * @Auther: 赵繁旗
 * @Date: 2019/7/8 14:43
 * @Description:   元空间异常，由于取代的是永久带，其中存储的信息 为 ：
 *                              虚拟机加载的类信息；
 *                              常量池；
 *                              静态变量；
 *                              即时编译的代码
 *
 *      JVM  参数  ： -XX:MetaspaceSize=16m -XX:MaxMetaspaceSize=16M
 *
 *      此处应该是用到了本地缓存的技术；尝试调小VM 运行参数，但是显示 启动虚拟机是就出错，并且：MaxMetaspaceSize is too small
 */
public class MyMetaspace {

    static  class MetaspaceTest{}
    public static void main(String[] args) {

        for (int i = 0;   ; i++) {
            MetaspaceTest metaspaceTest = new MetaspaceTest();
            System.out.println("this is new metaspaceTest : "+ i);
        }


    }
}
