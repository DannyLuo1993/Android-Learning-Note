public class Employee implements Comparable{

    public int id;
    public String name;
    public int age;
    public double salary;

    public Employee(int id, String name, int age, double salary){
        this.id = id;
        this.salary = salary;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public int compareTo(Object o) {
        Employee employees = (Employee) o;
        if(this.id > employees.id){
            return 1;
        }else{
            return -1;
        }
    }
}
