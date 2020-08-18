import java.util.Scanner;

public class PayEmployee {


    public static void main(String[] args){
        int current_month = 8;
        double bonus = 0;
        Employee[] employees = new Employee[2];
        employees[0] = new OfficialEmployee(8,8,8);
        employees[1] = new TempEmployee(8,100);
        for(int i = 0; i < employees.length; i++){
            if(employees[i] instanceof OfficialEmployee){
                employees[i].setSalary(10000);
                Scanner input = new Scanner(System.in);
                System.out.println("please record the birth month of employ: ");
                int birthmonth = input.nextInt();
                if(birthmonth == current_month ){
                    bonus = 100; }

                System.out.println(employees[i].earning());

            }else{
                System.out.println("The salary of this employee is: " + employees[i].earning());
            }
            System.out.println(employees[i].earning());
        }
    }
}
