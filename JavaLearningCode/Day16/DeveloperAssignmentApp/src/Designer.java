public class Designer extends Employee{

    int bonus;

    public Designer(String name, String member_position, int age, int salary, int id, int bonus) {
        super(name, member_position, age, salary, id);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
