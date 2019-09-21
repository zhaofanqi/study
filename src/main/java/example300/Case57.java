package example300;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * ClassName Case57
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/24 17:15
 * @Description:  序列化与对象的克隆  当类的成员变量比较复杂，有多个可变引用类型，此时考虑使用序列化方式 当存在引用类型变量，则该变量也需要实现序列化接口
 */
public class Case57 {
    public static void main(String[] args) {
        Address2 address=new Address2("beijing ");
        Book3 b3 = new Book3("java", 15, address);
        ArrayList<Book3> book3s = new ArrayList<>();
        ObjectOutputStream outs=null;
        ObjectInputStream ins=null;
        Book3 b3parse=null;
        long l1 = System.currentTimeMillis();

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                outs = new ObjectOutputStream(baos);
                outs.writeObject(b3);
                ins = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
                Book3 book3 = (Book3) ins.readObject();
                for (int i =0;i<10 ;i++){
                book3s.add(book3);
                }
                outs.close();
                ins.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }


        long l2 = System.currentTimeMillis();
        System.out.println(l1);
        System.out.println(l2);

        long l3 = System.currentTimeMillis();
        book3s.clear();
        for (int i = 0; i <100000 ; i++) {
            try {
                book3s.add(b3.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        long l4 = System.currentTimeMillis();
        System.out.println(l3);
        System.out.println(l4);
    }
}
class Book3 implements Serializable,Cloneable {
    private String name;
    private int prices;
    private Address2 Address2;

    public Address2 getAddress() {
        return Address2;
    }

    public void setAddress(Address2 Address2) {
        this.Address2 = Address2;
    }

    public Book3(String name, int prices) {
        this.name = name;
        this.prices = prices;
    }

    public Book3() {
    }

    public Book3(String name, int prices, Address2 Address2) {
        this.name = name;
        this.prices = prices;
        this.Address2 = Address2;
    }

    @Override
    public String toString() {
        return "Book3{" +
                "name='" + name + '\'' +
                ", prices=" + prices +
                ", Address2=" + Address2 +
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
        Book3 Book3 = (Book3) o;
        return prices == Book3.prices &&
                Objects.equals(name, Book3.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prices);
    }

    @Override
    protected Book3 clone() throws CloneNotSupportedException {
        Book3 book = null;
        try {
            book = (Book3) super.clone();
            book.Address2 = (Address2) Address2.clone(); //深浅克隆的区别

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
        }
        return book;
    }
}

class Address2 implements Serializable,Cloneable {
    private String area;

    public Address2(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Address2{" +
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
        Address2 Address2 = null;

        try {
            Address2 = (Address2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
        }
        return Address2;
    }

}