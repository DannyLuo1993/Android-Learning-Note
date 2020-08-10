class Test05_Exer1{
    public static void main(String[] args){
        java.util.Scanner input = new java.util.Scanner(System.in);

        System.out.print("Please input your score: ");
        int score = input.nextInt();


        if (score>=60){
            System.out.print("Test passed");
        }else{
            System.out.print("Test failed");
        }
    }
}