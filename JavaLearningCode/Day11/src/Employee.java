public class Employee {

    String name;
    MyDate birthday;
    double salary;

    public double earning(){
        return salary;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }


}
