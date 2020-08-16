class Day08_Exer1{

    public static void main(String[] args){

        Student[] studentArray = new Student[3]; //创建Student数组，长度为3，初始化值为[null, null, null]
        for (int i = 0; i < studentArray.length; i++){

            java.util.Scanner input = new java.util.Scanner(System.in); // new Scanner对象，实现键盘输入功能
            studentArray[i] = new Student();
            System.out.println("please input the name of student " + (i+1) + ": ");
            studentArray[i].name = input.next();
            System.out.println("please input the score of student " + (i+1) + ": ");
            studentArray[i].score = input.nextInt();
 
        }

        //遍历数组显示学生信息
        for (int i = 0; i < studentArray.length; i++) {

            studentArray[i].printInfo();
        }

        //打印分割线
        System.out.println("-----------------------------------------------------");


        //使用冒泡排序对数组的学生成绩排序
        for(int i = 1; i < studentArray.length; i++){//外循环控制轮数
            
            //内循环控制
            /*
            当 i = 1, j=0,1 数组0位置与数组位置1比较， 数组位置1与数组位置2比较
            当 i = 2， j = 0， 数组位置0 与 数组位置1比较
            */

            for( int j = 0; j < studentArray.length - 1; j++){

                if(studentArray[j].score > studentArray[j+1].score){
                    Student temp = studentArray[j];
                    studentArray[j] = studentArray[j+1];
                    studentArray[j+1] = temp;

                }
            }
            

        }

         //遍历数组显示学生信息
         for (int i = 0; i < studentArray.length; i++) {

            studentArray[i].printInfo();
        }

    }
}

class Student{

    String name;
    int score;

    void printInfo(){
        System.out.println("Name: " + name + "Score: " + score);
    }

}