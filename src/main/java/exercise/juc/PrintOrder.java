package exercise.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName PrintOrder
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/1 14:53
 * @Description: 用于按照指定顺序打印输出
 * 题目来源 https://leetcode-cn.com/problems/print-in-order/
 */
public class PrintOrder {

    private volatile String threadNum = String.valueOf(1);
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        PrintOrder printOrder = new PrintOrder();

        for (int i = 1; i < 4; i++) {
            new Thread(() -> {
                printOrder.printA();
            }, String.valueOf(i)).start();
            new Thread(() -> {
                printOrder.printB();
            }, String.valueOf(i)).start();
            new Thread(() -> {
                printOrder.printC();
            }, String.valueOf(i)).start();
        }


    }


    private void printA() {
        try {
            lock.lock();
            while (!threadNum.equals("1")) {
                conditionA.await();
            }
            threadNum = String.valueOf(2);
            conditionB.signal();
            System.out.println("ONE");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printB() {
        try {
            lock.lock();
            while (!threadNum.equals("2")) {
                conditionB.await();
            }
            threadNum = String.valueOf(3);
            conditionC.signal();
            System.out.println("TWO");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printC() {
        try {
            lock.lock();
            while (!threadNum.equals("3")) {
                conditionC.await();
            }
            threadNum = String.valueOf(1);
            conditionA.signal();
            System.out.println("THREE");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
