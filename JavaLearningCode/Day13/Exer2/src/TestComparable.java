import java.util.Arrays;

public class TestComparable {

    public static void main (String[] args){

        Employee[] employees = new Employee[3];
        employees[0] = new Employee(2,"Walter", 29, 6000);
        employees[1] = new Employee(1, "Danny", 27,8000);
        employees[2] = new Employee(3,"Victor",24,6000);
        Arrays.sort(employees);
        for(int i = 0; i<employees.length ; i++){
            System.out.println(employees[i]);
        }



    }
}
