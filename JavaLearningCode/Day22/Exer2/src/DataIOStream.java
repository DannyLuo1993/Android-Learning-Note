import java.io.*;

public class DataIOStream {

    public void testOutputStream() throws IOException{
        int num = 10;
        char c = 'G';
        double d = 88.00;
        String info = "I am pain";
        boolean good = true;

        FileOutputStream fos = new FileOutputStream("data.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(num);
        dos.writeChar(c);
        dos.writeDouble(d);
        dos.writeUTF(info);
        dos.writeBoolean(good);

    }

    public void testInputStream() throws IOException{
        FileInputStream fis = new FileInputStream("data.dat");
        DataInputStream dis = new DataInputStream(fis);

        int num = dis.readInt();
        char c = dis.readChar();
        double d = dis.readDouble();
        String info = dis.readUTF();
        boolean good = dis.readBoolean();
        System.out.println(num);
        System.out.println(c);
        System.out.println(d);
        System.out.println(info);
        System.out.println(good);
    }
}
