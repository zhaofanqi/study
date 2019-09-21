package example300;

/**
 * ClassName test1
 *
 * @Auther: 赵繁旗
 * @Date: 2018/7/11 17:34
 * @Description:测试增强型for 循环和普通for循环
 */
public class TestRegexp {
    public static void main(String[] args) {
        String str = "ABCDGHJKHasdfdsfk2349234^&I*&O(*";
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        String[] split = str.split("[a-z]");
        String[] split1 = str.split("[0-9]");
        String[] split2 = str.split("^[a-z][0-9]$");
        System.out.println("Upper"+split[0].length());
        int length = split1[0].length();
        int length1 = split[0].length();
        int i = length1 - length;
        System.out.println("lower"+i);
        System.out.println(split[0]);
        System.out.println(split2[0]);
       // System.out.println("qita"+split1[0].length());


    }
}
