import java.util.ArrayList;
import java.util.HashMap;

import static sun.misc.GThreadHelper.lock;
import static sun.misc.GThreadHelper.unlock;

/**
 * ClassName SingletonDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/4/21 21:24
 * @Description:
 */

class SinglePer{
    private static volatile  SinglePer singlePer;
    private SinglePer(){
        System.out.println(Thread.currentThread().getName()+"this is private contruct");
    }

    public static SinglePer getInstance(){
        if (singlePer==null){
//          lock();
          if(singlePer==null){
                singlePer  = new SinglePer();
            }
//            unlock();
        }

        return singlePer;
    }

}

public class SingletonDemo {

    public static void main(String[] args) {

//        System.out.println(SinglePer.getInstance()==SinglePer.getInstance());
//        System.out.println(SinglePer.getInstance()==SinglePer.getInstance());
//        System.out.println(SinglePer.getInstance()==SinglePer.getInstance());
//        System.out.println(SinglePer.getInstance()==SinglePer.getInstance());


        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        for (int i = 1; i <=200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SinglePer.getInstance();
                }
            },String.valueOf(i)).start();
        }


    }
}
