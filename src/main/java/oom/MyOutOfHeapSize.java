package oom;

/**
 * ClassName MyOutOfHeapSize
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 23:41
 * @Description:  堆内存溢出
 * VM option 参数 -Xms10m -Xmx10m
 */
public class MyOutOfHeapSize {
    public static void main(String[] args) {
        byte[] bytes = new byte[11 * 1024 * 1024];
    }
}
