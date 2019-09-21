package example300;

import java.util.ArrayList;

/**
 * @Auther: 赵繁旗
 * @Date: 2018/5/26 00:03
 * @Description: 快速排序 ：找分界点元素值，一次轮训会讲小于该分界点的值都存放在左边，大于该分界点的值都存放在右边
 */

public class case39_good {
    public static void main(String[] args) {
        Integer[] arr = {7,4,5,9,8,3,6,1,2};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
       /* int a =0;
        for (int k = 0; k <99 ; k++) {
            a=a++;
            System.out.println("inner a is :"+a );
        }
        System.out.println("this is "+a );*/
    }

    private static void quickSort(Integer[] arr, int lowIndex, int highIndex) {
        int lo=lowIndex;
        int hi=highIndex;
        int mid;  //设计到递归调用，取的值应该和传入参数有关

        if (lowIndex<highIndex) {
            //一次循环排序结束，可以分为2个数组+中间值
            while (lo<=hi){
                mid=arr[(lowIndex+highIndex)/2];
                while (lo<highIndex&&arr[lo]<mid) ++lo;//从前往后找到第一个元素（ 大于分界值最小的索引）
                while (hi>lowIndex&&arr[hi]>mid) --hi;//从后往前找到第一个元素小于分解值的最大索引
                if(lo<=hi) { //先判断是否仍是左小右大，找到以后需要交换2个元素的位置，同时都向内进一步
                    swap(arr, lo, hi);
                    ++lo;
                    --hi;
                }
            }
            //进行递归操作
            if (lowIndex<hi) quickSort(arr,lowIndex,hi);//注意是hi而不是 lo.因hi一直是自减的，它就是小于分解值的最大索引
            if (highIndex>lo) quickSort(arr,lo,highIndex);

        }


    }

    private static void swap(Integer[] arr, int lo, int hi) {
        int temp = arr[lo];
        arr[lo] = arr[hi];
        arr[hi] = temp;

    }

}
