package example300;

import java.util.Random;

/**
 * ClassName case37
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/12 06:43
 * @Description:   选择排序法： 每遍历一次找到该数组中的最大（小）值，并放到数组的结尾！
 */
public class case37 {
    public static void main(String[] args) {
        int[] arr = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            arr[i]= random.nextInt(30);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }

        for (int i = 1; i <arr.length ; i++) {
            int maxIndex=0;
            for (int j = 0; j <=arr.length-i ; j++) {
                if(arr[j]>arr[maxIndex]) maxIndex=j;
            }
            int tmp=arr[arr.length-i];
            arr[arr.length-i]=arr[maxIndex];
            arr[maxIndex]=tmp;
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }
}
