package example300;

/**
 * ClassName Case49
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/23 22:00
 * @Description: 构造器的重载
 */
public class Case49 {
    public static void main(String[] args) {
        Case49Demo case49Demo1 = new Case49Demo(5);
        Case49Demo zhaofan = new Case49Demo("zhaofan");
        System.out.println("  construct 1 "+case49Demo1);
        System.out.println("construct 2 "+zhaofan);


    }
}
class Case49Demo{
    private int age;
    private String name;

    public Case49Demo() {
    }

    public Case49Demo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Case49Demo(int age) {
        this.age = age;
    }

    public Case49Demo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Case49Demo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
