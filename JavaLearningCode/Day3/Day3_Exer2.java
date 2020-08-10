class Day3_Exer2 {
    public static void main(String[] args){

        int number_one = (int)(Math.random() * 6 + 1);
        int number_two = (int)(Math.random() * 6 + 1);
        int number_three = (int)(Math.random() * 6 + 1);
        System.out.print(number_one);
        System.out.print(number_two);
        System.out.println(number_three);
        int sum = number_one + number_two + number_three;
        System.out.println(sum);

        if(number_one == number_two && number_one == number_three && number_two == number_three){
            System.out.println("All kill");
        }else if(sum > 9){
            System.out.println("Big");
        }else{
            System.out.println("Small");
        }
    }
}