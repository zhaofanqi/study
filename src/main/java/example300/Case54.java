package example300;

/**
 * ClassName Case54
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/24 14:37
 * @Description:   假克隆 ,此处的
 */
public class Case54 {
    public static void main(String[] args) {
        Book1 book1 = new Book1("java",20);
        Book1 book2=book1;
        book2.setName("kafka");
        System.out.println(book1);
        System.out.println(book2);
        book1.setName("bigdata");
        System.out.println(book1);
        System.out.println(book2);
    }
}



