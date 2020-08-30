package javabean;


public class Employee {

    private int id;
    private String name;
    private int age;
    private int salary;
    private Status status;

    public Employee() {
    }

    public Employee(int id, String name, int age, int salary, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public String getInfo(){
        return id + "\t" + name  + "\t" + age + "\t" + salary + "\t" + getStatus();
    }
}


