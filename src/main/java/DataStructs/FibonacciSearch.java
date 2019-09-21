package DataStructs;

import java.util.Arrays;

/**
 * ClassName FIbonacciSearch
 *
 * @Auther: 赵繁旗
 * @Date: 2019/9/7 12:56
 * @Description:
 */
public class FibonacciSearch {
    static int maxsize=20;
    public static void main(String[] args) {
        int[] arr = new int[80];
        for (int i = 1; i < 81; i++) {
            arr[i - 1] = i;
        }
        int findValue = 80;

        int i = fibonacciSearch(arr, findValue);
        System.out.println(i);
        /*int[] a = f(8);
        System.out.println(Arrays.toString(a));*/
    }

    private static int[] f() {
        int[] f = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxsize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    private static int fibonacciSearch(int[] arr, int findValue) {


        int mid = 0;
        int low = 0;
        int high = arr.length - 1;

        int[] f = f();
        int k = 0;


        //第一步补齐待查找数组的长度, 并且补充位置为数组的最后一个元素
        while (high>f[k]-1){
            k++;
        }
        int[] temps = Arrays.copyOf(arr, f[k]);
        for (int i = arr.length; i < temps.length; i++) {
            temps[i]=arr[high];
        }

        // 开始比较并查找
        // f(k)=f(k-1)+f(k-2);，可以理解为：有前f(k-1）个元素 和后 f(k-2)个元素
        //n=f(k)-1; n 为数组的长度，f为斐波那契表达式函数
        while (low <= high) {
            mid=low+f[k-1]-1;
            if (temps[mid]<findValue){//说明这个数值在后半部分
                low=mid+1;
                k-=2;
            }else if(temps[mid]>findValue){
                k-=1;
            }else {//说明已经找到，但是由于原来的数组是被扩充过的，需要确定正确位置,想一想，由于数组是被扩充后的，每次找的都是切割点，可能在最后一次找到数据的
                //的切割点就是目标数值了。那么此时，这个切割点的数值是大于 high的
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }

        }


        return -1;
    }
}
