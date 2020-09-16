import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 从键盘输入词语
 * 发送给服务器
 * 接收服务器返回的金正恩
 */

public class TestClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("192.168.11.126", 8989);

        Scanner input = new Scanner(System.in);
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while(true){
            System.out.println("Please input a word: ");
            String word = input.next();

            printStream.println(word);
            if("bye".equals(word)){
                break;
            }

            String result = br.readLine();
            System.out.println("Reserve Word returned by server: " + result);
        }

        input.close();
        socket.close();

    }

}
