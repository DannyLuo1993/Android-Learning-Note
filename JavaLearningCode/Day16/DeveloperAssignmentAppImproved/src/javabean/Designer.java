package javabean;

public class Designer extends Programmer{

    private int bouns;


    public Designer() {
    }

    public Designer(int id, String name, int age, int salary, Status status, int bouns) {
        super(id, name, age, salary, status);
        this.bouns = bouns;
    }

    public int getBouns() {
        return bouns;
    }

    public void setBouns(int bouns) {
        this.bouns = bouns;
    }

    @Override
    public String toString() {
        return getInfo() + "\t" + bouns;
    }
}
