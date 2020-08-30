package javabean;

public class Arichitect extends Designer{


    private int stock;


    public Arichitect() {
    }

    public Arichitect(int id, String name, int age, int salary, Status status, int bouns, int stock) {
        super(id, name, age, salary, status,bouns);
        this.stock = stock;
    }



    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getInfo() + "\t" + stock;
    }
}
