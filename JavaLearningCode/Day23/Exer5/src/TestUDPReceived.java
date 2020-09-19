import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestUDPReceived {

    public static void main (String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(9999);

        byte[] data = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramSocket.receive(datagramPacket);

        byte[] message = datagramPacket.getData();
        int len = datagramPacket.getLength();
        //Let's date Sunday.
        System.out.println(new String(message, 0, len));
        System.out.println("----------");
        //[B@74a14482
        System.out.println(message);

        datagramSocket.close();
    }
}
