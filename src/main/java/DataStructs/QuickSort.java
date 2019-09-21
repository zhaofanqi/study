package DataStructs;

import java.util.Arrays;
import java.util.Random;

/**
 * ClassName QuickSort
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/31 15:05
 * @Description: 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
//        int[] arr = {7, 4, 5, 9, 8, 3, 6, 1, 2};

        Random random = new Random();

        for (int i = 0; i <80000 ; i++) {
            arr[i]=(int)random.nextInt(80000);
        }

        long l1 = System.currentTimeMillis();
        System.out.println(l1);
        quickSort(arr, 0, arr.length - 1);
        long l2 = System.currentTimeMillis();
        System.out.println(l2);
        System.out.println(l2-l1);
        // System.out.println(Arrays.toString(arr));

    }

    private static void quickSort(int[] arr, int lowIndex, int highIndex) {//这么创建方法是由于后面设计递归，都需要指定位置
        int lo = lowIndex;//lo 会被操作，而lowIndex 用于记录它下次数据的范围
        int hi = highIndex;
        int temp = Integer.MAX_VALUE;//用于交换的中间值
        int mid = arr[(lowIndex + highIndex) / 2]; // 此处如果仅仅是 mid =(lowIndex+highIndex)/2 则记住的是位置，而不是一开始时该位置的值

        while (lo < hi) {//控制一次循环是否结束
            while (lo < highIndex && arr[lo] < mid) lo++;//左边找到第一个比中间值大的
            while (hi > lowIndex && arr[hi] > mid) hi--;//右边找到第一个比中间值小的
            if (lo <= hi) {//说明仍然没有越过中间值
                temp = arr[lo];
                arr[lo] = arr[hi];
                arr[hi] = temp;
                ++lo;
                --hi;
            }
        }
        if (lowIndex < hi) quickSort(arr, lowIndex, hi);
//
        if (highIndex > lo) quickSort(arr, lo, highIndex);


    }
}
