package DataStructs;

import java.io.*;

/**
 * ClassName sparseArray
 *
 * @Auther: 赵繁旗
 * @Date: 2019/8/7 06:40
 * @Description: 原始的二维数组--->稀疏数组---->落盘文件
 * 原始的二维数组<---稀疏数组<----落盘文件
 */
public class sparseArray {

    private static boolean aBoolean;

    public static void main(String[] args) {
        //原始的二维数组--->稀疏数组
        int[][] origin = new int[10][10];
        origin[1][1] = 2;
        origin[2][2] = 3;
        origin[7][9] = 10;
        //遍历原始二维数组 得到非0 值的个数 sum ，用于创建 稀疏数组 [sum+1][3]
        int sum = 0;
        for (int[] ints : origin) {
            for (int ele : ints) {
                System.out.print(ele + " ");
                if (ele != 0) sum += 1;
            }
            System.out.println();
        }

        System.out.println(sum);
        //创建稀疏数组
        int[][] sparse = new int[sum + 1][3];
        sparse[0][0] = 10;
        sparse[0][1] = 10;
        sparse[0][2] = sum;
        //遍历原始数组并将非0数据填充入稀疏数组
        int row = 1;
        for (int j=0; j<10;j++) {
            for (int i = 0; i < 10; i++) {
                if (origin[j][i]!=0) {
                    sparse[row][0] = j ;
                    sparse[row][1] = i;
                    sparse[row][2] = origin[j][i];
                    row+=1;
                }
            }
        }

        //遍历稀疏数组查看数据分布；
        for (int i = 0; i < sparse.length; i++) {
            System.out.println("开始遍历稀疏数组");
            System.out.printf("%d\t%d\t%d\t",sparse[i][0],sparse[i][1],sparse[i][2]);
        }


        //将稀疏数组数据通过io流写入文件
        StringBuilder sb = new StringBuilder(1024);

        File file = new File("/Users/zhaofanqi/workspace/case1/test.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            System.out.println("开始遍历稀疏数组");
            for (int i = 0; i < sparse.length; i++) {
                System.out.printf("%d\t%d\t%d\t",sparse[i][0],sparse[i][1],sparse[i][2]);
                sb.append(sparse[i][0]+"\t"+sparse[i][1]+"\t"+sparse[i][2]);
                sb.append("\n");
            }
            fos.write(sb.toString().getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }




        // TODO 数组+集合 也不熟悉
        // TODO io流很不熟悉，需要加强
        // TODO 还有是时间格式处理；


        System.out.println("----------稀疏数组转------------");
        /**
         * 稀疏数组转二维数组
         *  0）读取文件中的内容并写入稀疏数组
         *  1）依据稀疏数组第一行得到原始数组的大小
         *  2）遍历稀疏数组并将得到的值写入新二维数组中
         *  3） 遍历新二维数组进行查看
         */


        try {
            FileInputStream fis = new FileInputStream("/Users/zhaofanqi/workspace/case1/test.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
           // System.out.println("得到有多少行： "+br.lines().count()); 从br中取出一次就没了！！

//解决br 的line后只可以操作一次问题
            int[][] newSparse=new int[4][3];
            //System.out.println(br.readLine());
            System.out.println("新数组的行数为："+newSparse.length);
            int newRow=0;
            String line;
            //读取文件中的内容并写入稀疏数组
            while ((line=br.readLine())!=null){
                //System.out.println(br.readLine());
                String[] split = line.split("\t");
                newSparse[newRow][0]=Integer.valueOf(split[0]);
                newSparse[newRow][1]=Integer.valueOf(split[1]);
                newSparse[newRow][2]=Integer.valueOf(split[2]);
                newRow+=1;
            }
            System.out.println("开始遍历xinde 稀疏数组");
            for (int i = 0; i < newSparse.length; i++) {
                System.out.printf("%d\t%d\t%d\t",newSparse[i][0],newSparse[i][1],newSparse[i][2]);
            }
            System.out.println("遍历xinde 稀疏数组 jieshu");
            //1）依据稀疏数组第一行得到原始数组的大小
            int[][] xinOrigin = new int[newSparse[0][0]][newSparse[0][1]];
            //2）遍历稀疏数组并将得到的值写入新二维数组中
            for (int i = 1; i < newSparse.length; i++) {
                //System.out.printf("%d\t%d\t%d\t",newSparse[i][0],newSparse[i][1],newSparse[i][2]);
                xinOrigin[newSparse[i][0]][newSparse[i][1]]=newSparse[i][2];
            }
            //3） 遍历新二维数组进行查看
            for (int[] ints : xinOrigin) {
                for (int ele : ints) {
                    System.out.print(ele + " ");
                }
                System.out.println();
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }







}
