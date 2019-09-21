package oom;

import com.sun.xml.internal.stream.util.BufferAllocator;
import sun.misc.VM;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * ClassName MyOutOfDirectBuffer
 *
 * @Auther: 赵繁旗
 * @Date: 2019/7/3 20:53
 * @Description:  jdk1.8开始的，元空间存在堆外内存中，DirectBuffer 用于描述
 *
 * 如果没设置-XX:MaxDirectMemorySize，则默认与-Xmx参数值相同
 *
 * 参考博客  堆外内存与堆内内存详解 ，网址：https://blog.csdn.net/ZYC88888/article/details/80228531
 * VM option 参数 ：-XX:MaxDirectMemorySize=10m
 *
 * 报错信息  java.lang.OutOfMemoryError: Direct buffer memory
 */
public class MyOutOfDirectBuffer {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024));
        System.out.println("配置的maxDirectMemory"+(VM.maxDirectMemory() / (1024 * 1024))+" MB ");


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12 * 1024 * 1024);
    }
}
