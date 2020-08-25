public class Architect extends Employee{

    int bonus;
    int stock;

    public Architect(String name, String member_position, int age, int salary, int id, int bonus, int stock) {
        super(name, member_position, age, salary, id);
        this.bonus = bonus;
        this.stock = stock;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
