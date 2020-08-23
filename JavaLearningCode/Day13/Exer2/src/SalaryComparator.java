import java.util.Comparator;

public class SalaryComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Employee employee = (Employee) o1;
        Employee other = (Employee) o2;

        if(employee.salary > other.salary){
            return 1;
        }else if(employee.salary < other.salary){
            return -1;
        }else{
            return 0;
        }

    }
}
