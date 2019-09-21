package DataStructs;

/**
 * ClassName HashTabDemo
 *
 * @Auther: 赵繁旗
 * @Date: 2019/9/7 14:40
 * @Description:
 *
 *  使用hashtable+二叉树 或者  hashtable+链表实现 快速查找；本例采用 hashtable+链表 实现
 */
public class HashTabDemo {
    public static void main(String[] args) {
        Hashtab hashtab = new Hashtab(10);
        Emp insertEmp=null;
        for (int i = 0; i <100 ; i++) {
            insertEmp=new Emp(i,i+"--");
            hashtab.add(insertEmp);
        }
        System.out.println("----开始输出----");
        hashtab.list();
    }
}

//定义 hashtab 用于整合，实现最终的功能
class Hashtab{
    public LinkedEmp[] arrLinkedEmp;
    public int maxSize;//用于确定 hashtab的长度，并可以用于确定新增的元素在那个链表中

    public Hashtab(int maxSize) {
        this.maxSize=maxSize;
        //this.arrLinkedEmp = new LinkedEmp[maxSize];
        this.arrLinkedEmp = new LinkedEmp[maxSize];

    }
    // 往数组中添加 元素，
    public void  add(Emp emp){
        int i = hashFun(emp);
        //System.out.println(i);
        LinkedEmp linkedEmp;
        if (null==arrLinkedEmp[i]){
            linkedEmp=new  LinkedEmp();
        }else {
            linkedEmp=arrLinkedEmp[i];
        }

       // System.out.println(emp);
        //System.out.println(linkedEmp);
        //arrLinkedEmp[i].add(emp);
        linkedEmp.add(emp);
    }

//    查看数组中元素
    public void list(){
        for (int i = 0; i < maxSize; i++) {
            System.out.println(arrLinkedEmp[i]);
            arrLinkedEmp[i].list();
        }
    }
    // 用于确定待加入元素应该在的链表
    public int hashFun(Emp emp){
        System.out.println(emp.id);
        System.out.println(maxSize);
        return emp.id%maxSize;
    }

}

// 定义链表
class LinkedEmp{
    public Emp head=null;//head  表示 链表的头元素
    //添加元素的方法
    public void add(Emp emp){
        System.out.println("jinru fangf l ");
        if (null==head){
            head=emp;
        }
        Emp curEmp=head;
        while (true){
            if (null==curEmp.next){
                break;
            }
            curEmp=curEmp.next;
        }
        curEmp.next=emp;
        head=curEmp;
    }

    public LinkedEmp() {
    }

    public void list(){
        if (null==head) {
            System.out.println("链表为空");
        }else {
            Emp curEmp=head;
            while (true){
                System.out.printf("id=%d\t name=%s ;",curEmp.id,curEmp.name);
                if (curEmp.next==null){
                    break;
                }
                curEmp=curEmp.next;
            }
        }



    }





}


//定义资源类
class Emp{
    public  int id ;
    public String name;
    public Emp next;
    public void Emp(){
        this.next=null;
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

