import java.io.*;

public class TestSerializable {

    public void testOutputStream() throws IOException {
        User u = new User("Danny", "123456",27,'A');

        FileOutputStream fos = new FileOutputStream("obj.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(u);
    }

    public void testInputStream() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("obj.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        System.out.println(obj);

        ois.close();
        fis.close();
    }
}
