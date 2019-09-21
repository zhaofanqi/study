package example300;

/**
 * ClassName Case48
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/23 21:57
 * @Description:  重载 : 方法签名不同（方法参数个数，参数类型，参数顺序）
 */
public class Case48 {
    public static void main(String[] args) {
        pri(5);
        pri(5,3);
    }

    private static void pri(int i, int j) {
        System.out.println(i-j);
    }

    private static void pri(int i) {
        System.out.println(i*i);
    }
}
