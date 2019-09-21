package DataStructs;

import java.util.Arrays;
import java.util.Random;

/**
 * ClassName MergeSort
 *
 * @Auther: 赵繁旗
 * @Date: 2019/9/1 16:34
 * @Description: 归并排序：时间复杂度为 线性滴；将原数组不断分割取一半进行分割，直到最后都变成单个元素，再按照相反的顺序
 * 进行合并 如 8 5 1 3 4 2 6 7
 * 会被分为  8 5    1  3   4 2   6 7
 * 合并 ：  5 8    1 3    2 4   6 7
 * 合并     1 3 5 8     2 4 6 7
 * 合并     1 2 3 4 5 6 7 8 (一共合并 7 次)
 */
public class MergeSort {
    static  int count=0;
    static  int c=0;
    public static void main(String[] args) {

       // int[] arr = {8, 5, 1, 3, 4, 2, 6, 7};
        int[] arr = new int[8000000];
       // int[] arr=new int[10];
        Random random = new Random();
        for (int i = 0; i < 8000000 ; i++) {
            arr[i]=(int)random.nextInt(8000000);
        }
//8*1000000 800万的数据只要1436ms就可以完成排序
        long l1 = System.currentTimeMillis();
        System.out.println(l1);


        int[] temp = new int[arr.length];//合并用到的临时空间
        //合并过程需要的参数： 左边数据的索引，右边数据的索引
       // merge(arr, 0, 7, temp);
        //用于将数组进行分割的
        mergeSort(arr,0,arr.length-1,temp);

        long l2 = System.currentTimeMillis();
        System.out.println(l2);

        System.out.println(l2-l1);
        System.out.println(count);
        //System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        int mid=0;
        if (left<right){
                mid=(left+right)/2;
                mergeSort(arr,left,mid,temp);

                mergeSort(arr,mid+1,right,temp);

                merge(arr,left,mid,right,temp);
++count;
          //  System.out.println("执行分开次数: "+ ++count);
        }




    }

    private static void merge(int[] arr, int left,int mid, int right, int[] temp) {
       // System.out.println("=====c======"+ ++c);
        int i = left; //用于记录左边数组的下标
        int j = mid+1;//用于记录右边数组的下标
        int t = 0;//每一次进来都从临时数组的第一个开始存入
        //arr[i] 为左边数组的第一个元素， arr[mid]为右边数组的第一个元素
        //经过while 循环会将一个数组中的元素全部添入了
        while (i <=mid&& j<= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i += 1;
                t += 1;
            } else {
                temp[t] = arr[j];
                j += 1;
                t += 1;
            }
        }

//如果出现一边的数据都填入，则将另外一遍的数据按顺序添入临时数据即可，未考虑到仅仅只有2个元素时，采用这这种办法存在bug
        /*if (i == mid) {
            while (j <= right) {//将右边的数据一次添入
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        } else {
            while (i <= mid) {
                temp[t] = arr[i];
                i += 1;
                t += 1;
            }
        }*/


        while (i<=mid){
            temp[t] = arr[i];
            i += 1;
            t += 1;
        }
        while (j<=right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 将临时数组的值拷贝回原数组中
        t=0;//此处重置t为 0  是因为临时数组存入值时，每次都从下标 0 开始存入
        int tempLeft=left;

        while (tempLeft<=right){
            arr[tempLeft]=temp[t];
            t+=1;
            tempLeft+=1;
        }

    }
}
