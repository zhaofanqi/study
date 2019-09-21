package juc;

import Bean.Person;

/**
 * ClassName ReferDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/24 19:32
 * @Description:  地址引用，变量的拷贝
 * 考察的知识点：变量的作用域以及 变量在jvm中的存储位置
 *
 */


public class ReferDemo {
    public  void changeValue_1(int age){
        age =30;
    }
    public void changeValue_2(Person person){
        person.setPersonName("xxxx");
    }
    public void changeValue_3(String str){
        str="xxx";
    }
    public static void main(String[] args) {
        ReferDemo test = new ReferDemo();
        int age=20;
        test.changeValue_1(age);
        /**
         *变量 age  的作用域仅仅在 main 线程中的main 方法中；
         * 定义在某一方法a内的变量 n ，在传递到方法b时，传递的仅仅是该 n 的地址的拷贝值,该值存在的生命周期为该方法 b 调用结束而结束
         *          即随着 方法b 从栈中弹出 而变量拷贝值的生命周期结束
         *
         */
        System.out.println("age is : \t"+age);

        Person person = new Person("abc");
        /**
         * 对象实例变量，是引用地址变量，传递的是地址而不是 该地址对应的值。
         */
        test.changeValue_2(person);
        System.out.println("personName ---"+person.getPersonName());

        String str="abc";
        /**
         * 此处需要注意：字符串是存放在 jvm 的常量池中的，栈中某一方法的弹出，该方法中创建的字符串变量仍然存在
         * changeValue_3() 在栈中开始指向的是 abc 的地址,该方法中修改了该值，在jvm中，对于不存在的
         *                  字符串常量采用新建的方式，从而，原来的main线程仍然指向abc地址，而栈中的方法changValue_3则指向xxx地址
         *          而由于此处查看的是 变量str，它始终指向的都是字符串 "abc"
         */
        test.changeValue_3(str);
        System.out.println("this is  str String :\t"+str );
    }
}
