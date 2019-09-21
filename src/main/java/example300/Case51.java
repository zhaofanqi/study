package example300;

import java.util.HashSet;
import java.util.Objects;

/**
 * ClassName Case51
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/24 11:09
 * @Description:  比较2个对象是否相等  包括了 case52,53 内容
 */
public class Case51 {
    public static void main(String[] args) {
        Book1 b1 = new Book1("java", 10);
        Book1 b2 = new Book1("java", 10);

        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b1.hashCode());
        System.out.println(b2.hashCode());
        System.out.println(b1.hashCode()==b2.hashCode());
        System.out.println(b1.equals(b2));
        HashSet<Book1> book1s = new HashSet<>();
        book1s.add(b1);
        book1s.add(b2);


        System.out.println(book1s);
    }
}
class Book1{
    private String name;
    private int  prices;

    public Book1(String name, int prices) {
        this.name = name;
        this.prices = prices;
    }

    public Book1() {
    }


    @Override
    public String toString() {
        return "Book1{" +
                "name='" + name + '\'' +
                ", prices=" + prices +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book1 book1 = (Book1) o;
        return prices == book1.prices &&
                Objects.equals(name, book1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prices);
    }
}
