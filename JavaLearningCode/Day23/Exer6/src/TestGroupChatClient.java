import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestGroupChatClient {

    public static void main (String[] args) throws IOException {

        //
        Socket socket = new Socket("192.168.11.126", 9999);

        //
    }
}

class SendThread extends Thread{

    private Socket socket;

    public SendThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){

        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Please input message here: ");
            String message = input.nextLine();
            
        }
    }
}
