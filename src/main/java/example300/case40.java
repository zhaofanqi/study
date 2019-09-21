package example300;

import java.util.Random;

/**
 * @Auther: 赵繁旗
 * @Date: 2018/5/26 00:03
 * @Description:  直接插入排序  :将待排序对象 与已经排好序的对象进行比较，即
 *                  假设左小右大，那么拿已经拍好序的最后一个元素与待插入对象进行比较，若左边大，则继续与排好序的倒数第二个元素进行比较，一直比较
 *                      直到找到左边一个元素比它小，则此时找到待插入对象位置
 */
public class case40 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] ints = new int[20];

        for (int i = 0; i < ints.length; i++) {
            ints[i]=random.nextInt(20);
        }
        int[] intersection = intersection(ints);

        for (int i = 0; i <intersection.length ; i++) {
            System.out.printf("%d\t",intersection[i]);
        }
    }
    public static int[] intersection(int[] arrs) {
        int tmp ;//用于存储待比较 对象的值
        int j; //用于表示已经拍好序的最后一个元素的下标
        //i :表示开始插入元素，从1 开始，即第一个元素不用比较，从第二个元素开始比较
        for (int i = 1; i <arrs.length ; i++) {
            tmp=arrs[i];
            for(j=i-1;j>=0&&arrs[j]>tmp;j--){//j--表示，从已经排好序的最后以后开始比较
                arrs[j+1]=arrs[j];//由于判断条件中，是左边大于待比较对象，即需要将已经拍好序的对象向后移动一位
            }
             arrs[j+1]=tmp;//因为上面是执行完j--才跳出的
        }
        return arrs;
    }

}
