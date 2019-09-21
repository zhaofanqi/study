package example300;

import java.util.Random;

/**
 * ClassName Case50
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/23 22:06
 * @Description:  注意：static 变量是全局变量，所有对象共享，而 非static 修饰的变量为 成员变量，各自修改时，相互之间互不影响
 */
public class Case50 {
    public static void main(String[] args) {
        String[] arr={"java","spring boot","kafka"};
        for (int i = 0; i <5 ; i++) {
            new Book(arr[new Random().nextInt(3)]);
        }
        System.out.println(Book.getCounter());
    }
}
class Book{
    private static int counter=0;
    public  Book(String title){
        System.out.println("销售的图书为 ：" +title);
        counter++;
    }
    public  static  int getCounter(){
        return counter;
    }


}

