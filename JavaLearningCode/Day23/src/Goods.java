import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Goods implements Externalizable {

    private static String brand = "";
    private String name;
    private double price;
    private transient int sale;

    public Goods(String name, double price, int sale) {
        this.name = name;
        this.price = price;
        this.sale = sale;
    }

    public Goods() {
    }

    public static String getBrand() {
        return brand;
    }

    public static void setBrand(String brand) {
        Goods.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //To define the serializable properties and its order
        out.writeUTF(brand);
        out.writeUTF(name);
        out.writeDouble(price);
        out.writeInt(sale);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //To define the de-serializable properties and its order
        in.readUTF();
        in.readUTF();
        in.readDouble();
        in.readInt();
    }
}
