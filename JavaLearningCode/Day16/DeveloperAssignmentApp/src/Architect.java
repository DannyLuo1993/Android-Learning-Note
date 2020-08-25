public class Architect extends Employee{

    int bonus;
    int stock;

    public Architect(String name, int age, int salary, int bonus, int stock) {
        super(name, age, salary);
        this.bonus = bonus;
        this.stock = stock;
    }
}
