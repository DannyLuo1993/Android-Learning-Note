class Day08_Exer1{

    public static void main(String[] args){

        Student[] studentArray = new Student[3]; //����Student���飬����Ϊ3����ʼ��ֵΪ[null, null, null]
        for (int i = 0; i < studentArray.length; i++){

            java.util.Scanner input = new java.util.Scanner(System.in); // new Scanner����ʵ�ּ������빦��
            studentArray[i] = new Student();
            System.out.println("please input the name of student " + (i+1) + ": ");
            studentArray[i].name = input.next();
            System.out.println("please input the score of student " + (i+1) + ": ");
            studentArray[i].score = input.nextInt();
 
        }

        //����������ʾѧ����Ϣ
        for (int i = 0; i < studentArray.length; i++) {

            studentArray[i].printInfo();
        }

        //��ӡ�ָ���
        System.out.println("-----------------------------------------------------");


        //ʹ��ð������������ѧ���ɼ�����
        for(int i = 1; i < studentArray.length; i++){//��ѭ����������
            
            //��ѭ������
            /*
            �� i = 1, j=0,1 ����0λ��������λ��1�Ƚϣ� ����λ��1������λ��2�Ƚ�
            �� i = 2�� j = 0�� ����λ��0 �� ����λ��1�Ƚ�
            */

            for( int j = 0; j < studentArray.length - 1; j++){

                if(studentArray[j].score > studentArray[j+1].score){
                    Student temp = studentArray[j];
                    studentArray[j] = studentArray[j+1];
                    studentArray[j+1] = temp;

                }
            }
            

        }

         //����������ʾѧ����Ϣ
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