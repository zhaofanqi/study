package example300;

import java.util.Arrays;
import java.util.Objects;

/**
 * ClassName test2
 *
 * @Auther: 赵繁旗
 * @Date: 2019/5/18 19:35
 * @Description:
 */
public class TestFor {
    public static void main(String[] args) {
        /*int[]  arr= {'1','2','3','4','5'};
        for(int i=0;i<arr.length;i++){
            arr[i]*=10;
            System.out.println(arr[i]);
        }
        for(int i :arr){
            i*=5;
            System.out.println(i);
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }*/
        System.out.println(4%4);


        String a=new String("abcdefg");
        String b=new String("abcdefg");
        System.out.println(a==b);
        System.out.println(a.equals(b));

        System.out.println("abcdefg".hashCode());
        System.out.println(a.hashCode());
        System.out.println(Objects.hash(a));



        //测试 string 的hashcode()   与Arrays 的hashcode()

        System.out.println("another test");
        String d=new String("abcdefg");
        String[] e={"abcdefg"};
        System.out.println(d.hashCode());
        System.out.println(Arrays.hashCode(e));
    }
}
