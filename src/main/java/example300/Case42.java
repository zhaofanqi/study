package example300;

import java.util.Random;

/**
 * ClassName Case42
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/14 19:02
 * @Description:  数组反转  reverseArray
 *
 *          关键点：反转次数；哪两个元素之间反转
 */
public class Case42 {
    public static void main(String[] args) {
        int[] ints = new int[10];

        Random random = new Random();
        for (int i = 0; i <ints.length ; i++) {
            ints[i]=random.nextInt(20);
        }

        for (int i = 0; i <ints.length ; i++) {
            System.out.printf("%d\t",ints[i]);
        }
        int[] reArray=reverSeArray(ints);
        System.out.println("------------");
        for (int i = 0; i < reArray.length; i++) {
            System.out.printf("%d\t",reArray[i]);
        }

    }

    private static int[] reverSeArray(int[] ints) {
        int tmp;
        for (int i = 0; i <ints.length/2 ; i++) {
            tmp= ints[i];
            ints[i]=ints[ints.length-1-i];
            ints[ints.length-1-i]=tmp;
        }

        return ints;
    }

}
