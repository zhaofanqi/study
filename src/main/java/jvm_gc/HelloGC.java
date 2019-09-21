package jvm_gc;

/**
 * ClassName HelloGc
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 15:09
 * @Description:   boolean 类型参数 -XX:+/- PrintGCDetails  +表示开启，- 表示关闭
 *
 * jps -l 查看 运行的 java  线程号
 *  jinfo -flag PrintGCDetails
 *          使用jinfo 命令，
 *          -flag 表示想查看具体的运行参数
 *          PrintGCDetails：此处是是否打印GC，也可以是其他的运行参数
 *          线程号：查看对应的线程号
 *
 *         查看默认的 原空间大小为 ： -XX:MetaspaceSize=21807104  修改为  -XX:MetaspaceSize=1024m
 * 传递多个参数 在VMoption 中添加 -XX:+PrintGCDetails -XX:MetaspaceSize=1024m  注意参数是以空格隔开的
 *         java -XX:+PrintFlagsInitial  ： 查看初始默认配置参数
 *         java -XX:+PrintFlagsFinal -version  ： 查看更新后的配置参数 := 表示被修改了
 *         java -XX:+PrintCommandLineFlags -version 主要用来看垃圾回收器的种类+堆大小信息
 *         jinfo -flag ThreadStackSize 线程号   ： 查看单个线程栈的大小（若为0 ，表示使用的默认值）
 *
 *         -XX:SurvivorRatio=8  表示 Eden 占新生区的比例 即 80%，注意 survivorFrom = survivorTo
 *                          若 -XX：SurvivorRatio=4 ，则相当与 Eden：S0:S1=4:1:1，对于整个新生区即为 4：3：3
 *
 *         -XX:NewRatio=2 新生区占比为1 ，养老区占比为 2
 *                  -XX:NewRatio=4 新生区占比为1 ，养老区占比为 4
 *         -XX:MaxTenuringThreshold=15  默认值为15
 *
 *
 *         如何查看默认的垃圾回收器 +PrintFlagsFinal -version
 *
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("+==========");
//        System.out.println(Runtime.getRuntime().totalMemory()/(1024*1024));// 最大内存123
//        System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024));// 能够获取的最大内存 1820
       Thread.sleep(Integer.MAX_VALUE);
    }
}
