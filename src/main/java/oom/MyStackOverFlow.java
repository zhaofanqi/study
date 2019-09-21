package oom;

/**
 * ClassName MyStackOverFlow
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/29 23:34
 * @Description: 栈内存溢出错误。栈管运行，是个方法，-Xss设置的默认值为 512～1024Kb取决于机器
 */
public class MyStackOverFlow {
    public static void main(String[] args) {
        myStackOverFlowError();
    }

    private static void myStackOverFlowError() {
        myStackOverFlowError();// java.lang.StackOverflowError 方法调用自身，陷入死循环 从栈中溢出
    }
}
