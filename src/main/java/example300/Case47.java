package example300;

import java.util.Stack;

/**
 * ClassName Case47
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/14 19:20
 * @Description: 汉诺塔 ：  三根柱子，上面套住圆盘，只能小的在上，大的在下，从一个盘子，全都移动另外一个 盘子
 * //TODO  不会写
 */
public class Case47 {

    public static void main(String[] args) {
        Case47 case47 = new Case47();
        Stack<Integer> stack1 = new Stack<>();
        case47.push(stack1,5);
       // case47.print(stack);

        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();


    }

    public static void move(int n) {// 表示传入n 个盘子

    }

    public static void push(Stack stack, int n) {
        for (int i = n; i >0; i--) {
            stack.push(i);
        }
    }

    public static void print(Stack stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }


    }

}
