package example300;

import java.util.Scanner;

/**
 * ClassName TestForarr
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/11 22:42
 * @Description:
 */
public class TestForarr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生人数");

        int[] arr = new int[scanner.nextInt()];

        System.out.println("请输入学生成绩");
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            if (max < arr[i]) max = arr[i];
        }
        System.out.println("学生成绩最高为：" + max);
        //开始遍历数组并取出每个学生成绩与最大值的差值
        char level;
        for (int i = 0; i < arr.length; i++) {
            if (max - arr[i] < 10) {
                level = 'A';
                System.out.println(arr[i] + "\t 成绩登记为：" + level);
            } else if (max - arr[i] < 20) {
                level = 'B';
                System.out.println(arr[i] + "\t 成绩登记为：" + level);
            } else if (max - arr[i] < 30) {
                level = 'C';
                System.out.println(arr[i] + "\t 成绩登记为：" + level);
            } else {
                level = 'D';
                System.out.println(arr[i] + "\t 成绩登记为：" + level);
            }
        }
    }
}
