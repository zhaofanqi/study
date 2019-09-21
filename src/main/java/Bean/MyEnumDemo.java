package Bean;

import lombok.Getter;

/**
 * ClassName CountDownLockDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/27 16:19
 * @Description: 倒数并决定执行
 * <p>
 * <p>
 * CountDownLatch() 会一直计数，直到将给定数字减为零 才执行后续的语句
 */

public enum MyEnumDemo {

    ONE(1,"齐"),TWO(2,"楚"),

    THREE(3,"燕"),FOUR(4,"韩"),

    FIVE(5,"赵"),

    SIX(6,"魏");

//枚举类相当于一个本地数据库，ONE,TWO  这样的相当于一张张表，其中的字段相当于列

     private int resCode;

     private String resValue;

    MyEnumDemo(int resCode, String resValue) {
        this.resCode = resCode;
        this.resValue = resValue;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResValue() {
        return resValue;
    }

    public void setResValue(String resValue) {
        this.resValue = resValue;
    }

    MyEnumDemo() {
    }

    //枚举类有自己的遍历方式方法

    public static MyEnumDemo foreach_country(int resCode){
        MyEnumDemo[] values = MyEnumDemo.values();
        for (MyEnumDemo value : values) {
            if (value.resCode==resCode){
                return  value;
            }
        }
        return null;
    }

}
