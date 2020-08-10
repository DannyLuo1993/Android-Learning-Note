class Day4_Exer3{
    public static void main(String[] args){

        int random;
        int time_sum = 1;
        int num;
        
        random = (int)(Math.random() * 100);
        java.util.Scanner input = new java.util.Scanner(System.in);
        num = input.nextInt();
        while(random != num){
            time_sum +=1;
            if (random > num){
                System.out.println("Out of range_left_bound");
            }else{
                System.out.println("Out of range_right_bound");
            }
            num = input.nextInt();
        }

        System.out.println(time_sum);

    }
}