import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestFileSendClient {

    public static void main(String[] args) throws IOException {

        //new a socket to connect to server
        Socket socket = new Socket("192.168.31.154", 9999);

        //Load the file user want to upload to server
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the file path and its name that you want to upload: " );
        String fileName = input.nextLine();
        //File类，操作文件用
        File file = new File(fileName);


        //
        OutputStream out = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        //将数据送入输出流
        dataOutputStream.writeUTF(file.getName());

        //发送文件内容
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[1024];
        int len;
        while((len = fileInputStream.read(data)) != -1){
            dataOutputStream.write(data,0,len);
        }
        socket.shutdownOutput();

        //接收反馈
        InputStream in = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result = bufferedReader.readLine();
        System.out.println("result: " + result);

        socket.close();
        fileInputStream.close();
    }
}
