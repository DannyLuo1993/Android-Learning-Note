class Day3_Exer1{
    public static void main(String[] args){
        
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Please input a number: ");
        int number;
        number = input.nextInt();

        switch(number){

            case 0:
            System.out.println("Number zero");
            break;

            case 1: 
            System.out.println("Number one");
            break;

            case 2:
            System.out.println("Number two");
            break;

            case 3:
            System.out.println("Number three");
            break;

            case 4:
            System.out.println("Number four");
            break;

            case 5:
            System.out.println("Number five");
            break;

            case 6:
            System.out.println("Number six");
            break;

            case 7:
            System.out.println("Number seven");
            break;

            case 8:
            System.out.println("Number eight");
            break;

            case 9:
            System.out.println("Number other");
            break;

            default:
            System.out.println("Number out of range");
            break;
        }

    }
}