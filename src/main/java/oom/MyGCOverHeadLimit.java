package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName MyGCOverHeadLimit
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 23:50
 * @Description:  GC 执行时间长，但是回收的堆空间少，效果不佳，直接抛出错误
 * -Xms2m -Xmx2m -XX:+PrintGCDetails  堆内存设置小了还会出现报错为 堆内存不足
 *  -Xms20m -Xmx20m -XX:+PrintGCDetails  报错信息为 java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class MyGCOverHeadLimit {
    public static void main(String[] args) {
        int  i=0;
        List<String> list = new ArrayList<String>();

        //编写好的代码块想被try catch finally 使用command + option + t
        try {
            while (true){
                list.add(String.valueOf(i++).intern());//intern 是个字符串池
            }
        } catch (Throwable e) {
            System.out.println("================"+i);
            e.printStackTrace();
            throw e;


        }
    }
}
