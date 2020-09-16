import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

    public static void main(String[] args) throws IOException {

        //1. 开启服务器, 并将这个ServerSocket对象绑定到8989端口 - Communication End Point
        ServerSocket serverSocket = new ServerSocket(8989);

        //2. 接受一个客户端的连接 Communication End Point
        //监听连接到ServerSocket的socket对象，然后接收。
        Socket socket = serverSocket.accept();

        //3. 先获取输入输出流 - Communication Path
        //Returns an input stream for this socket.
        //Returns an output stream for this socket.
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();


        //从字节流对象 in 中读取字节数据，转成字符流
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        /**
         * 选择换行符作为词语的分割
         */
        PrintStream printStream = new PrintStream(out);


        String word;
        while( (word = br.readLine()) != null ){
            if("bye".equals(word)){
                break;
            }
            //如果不是bye，要反转并且返回
            StringBuilder stringBuilder = new StringBuilder(word);
            stringBuilder.reverse();
            //返回给客户端
            printStream.println(stringBuilder.toString());

        }

        socket.close();
        serverSocket.close();

    }
}
