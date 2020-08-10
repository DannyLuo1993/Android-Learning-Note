class Test07_Exer2{
    public static void main(String[] args){
        java.util.Scanner input = new java.util.Scanner(System.in);
        int score;
        System.out.print("Please input your score: ");
        score = input.nextInt();

        if(score == 100){
            System.out.println("Award a BMW car");
        }else if(score > 80){
            System.out.println("Award an iphone 11 pro");
        }else{
            System.out.println("GET OUT OF MY OFFICE");
        }
    }
}