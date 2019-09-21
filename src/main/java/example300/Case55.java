package example300;

import java.util.Objects;

/**
 * ClassName Case55
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/24 14:44
 * @Description: 对象的浅克隆 +对象的深克隆
 */
public class Case55 {
    public static void main(String[] args) {
        /*Book2 book1 = new Book2("java", 20);
        Book2 book2 = null;
        try {
            book2 = book1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("-----------");
        }
        book2.setName("kafka");
        System.out.println(book1);
        System.out.println(book2);
        book1.setName("bigdata");
        System.out.println(book1);
        System.out.println(book2);*/


        Book2 book2 = new Book2("java", 15, new Address("beijing"));
        Book2 book2_2=null;

       /* book2.setName("kafka");
        book2_2.setName("flume");
        System.out.println(book2);
        System.out.println(book2_2);*/

        System.out.println("modify  address");

        //book2.setAddress(new Address("上海"));
        try {
            book2_2 =book2.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {

        }
        book2.getAddress().setArea("shanghai");
       // book2_2.setAddress(new Address("heife"));
        System.out.println(book2);
        System.out.println(book2_2);

    }
}

class Book2 implements Cloneable {
    private String name;
    private int prices;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Book2(String name, int prices) {
        this.name = name;
        this.prices = prices;
    }

    public Book2() {
    }

    public Book2(String name, int prices, Address address) {
        this.name = name;
        this.prices = prices;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Book2{" +
                "name='" + name + '\'' +
                ", prices=" + prices +
                ", address=" + address +
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
        Book2 Book2 = (Book2) o;
        return prices == Book2.prices &&
                Objects.equals(name, Book2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prices);
    }

    @Override
    protected Book2 clone() throws CloneNotSupportedException {
        Book2 book = null;
        try {
            book = (Book2) super.clone();
           // book.address = (Address) address.clone(); //深浅克隆的区别

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
        }
        return book;
    }
}

class Address implements Cloneable {
    private String area;

    public Address(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Address{" +
                "area='" + area + '\'' +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Address address = null;

        try {
            address = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
        }
        return address;
    }
}
