import java.io.IOException;
import java.net.*;

public class TestUDPSend {

    public static void main (String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();

        String str = "Let's date Sunday.";
        byte[] message = str.getBytes();
        InetAddress ip = InetAddress.getByName("192.168.123.123");
        DatagramPacket datagramPacket = new DatagramPacket(message,0,message.length,ip,9999);

        datagramSocket.send(datagramPacket);
        System.out.println("Message sent");

        datagramSocket.close();
    }
}
