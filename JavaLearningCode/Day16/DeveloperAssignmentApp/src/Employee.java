public class Employee {

    String name;
    String member_position;
    int age;
    int salary;
    int id;

    public Employee(String name, String member_position, int age, int salary, int id) {
        this.name = name;
        this.member_position = member_position;
        this.age = age;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
