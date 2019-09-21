package DataStructs;

/**
 * ClassName ArrayQueue
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/10 16:27
 * @Description:n  队列：
 *                      特点：先进先出（控制开头，结尾的下表）  ；有大小；
 *                      用数组实现
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        Arrayqueue arrayqueue = new Arrayqueue(5);
        System.out.println(arrayqueue.isEmpty());
        System.out.println(arrayqueue.isFull());
        for (int i = 0; i <5 ; i++) {
            arrayqueue.addQueue(i);
        }
        arrayqueue.show();

    }
}

/**
 * 使用数组实现队列,默认 front ,last为-1
 */
class Arrayqueue{
    private int front; //表示数组的前一个下标
    private int last;   //表示数组的最后一个下标
    private int maxSize;//定义数组的最大大小即队列的大小
    private int[] arr; //用于保存 队列数据

    public Arrayqueue(int maxArraySize){
        maxSize=maxArraySize;
        arr=new int[maxArraySize];
        front=-1;
        last=-1;
    }
    //判断队列是否为空，满，增加，取出，第一个元素
    public Boolean isEmpty(){
        return front==last;
    }
    public boolean isFull(){
        return last==maxSize-1;
    }
    public void addQueue(int n ){
        if(isFull()){
            System.out.println("队列已经满了");
        }
        last++;
        arr[last]=n;
    }
    public int getQueue(){
        if (isEmpty()){
            System.out.println("队列为空，无法取出");
        }
        front++;
       return arr[front];
    }
    public int peek(){
        if (isEmpty()){
            System.out.println("队列为空，无法取出");
        }
        front++;
        return arr[front];
    }
    public void show(){
        if (isEmpty()){
            System.out.println("队列为空，无元素可展示");
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }


}