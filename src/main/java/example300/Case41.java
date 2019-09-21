package example300;

import java.util.Arrays;
import java.util.Random;

/**
 * ClassName Case41
 *
 * @Auther: 赵繁旗
 * @Date: 2018/5/26 00:08
 * @Description:  利用 工具类 Arrays
 */
public class Case41 {
    public static void main(String[] args) {
        int[] ints = new int[10];
        Random random = new Random();
        for (int i = 0; i <ints.length ; i++) {
            ints[i]=random.nextInt(20);
        }

        for (int i = 0; i <ints.length ; i++) {
            System.out.printf("%d\t",ints[i]);
        }
        Arrays.sort(ints);

        for (int i = 0; i <ints.length ; i++) {
            System.out.printf("%d\t",ints[i]);
        }
    }
}
