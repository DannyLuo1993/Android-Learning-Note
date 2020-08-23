import java.util.Scanner;

public class TestExer {

    private static int numa;
    private static int numb;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Please input number a: ");
            numa = input.nextInt();
            System.out.print("Please input number b: ");
            numb = input.nextInt();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
