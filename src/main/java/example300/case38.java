package example300;

import java.util.Random;

/**
 * ClassName case38
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/12 21:07
 * @Description: 冒泡排序 : 外层控制循环次数，里面相邻元素进行比较
 */
public class case38 {
    public static void main(String[] args) {
        int[] arr=new int[20];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i]=random.nextInt(30);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }

        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if(arr[j]>arr[j+1]){
                    int tmp=-1;
                    tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }

        System.out.println("排序后数据");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }
}
