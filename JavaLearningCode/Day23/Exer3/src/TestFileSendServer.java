import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestFileSendServer {

    public static void main(String[] args) throws IOException {

        //New a server socket, accept and save the client connection to socket object
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();

        //Create Stream
        InputStream in = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(in);


        //(1) Receive file name and its extension type.
        //(2) Generate new file name for server storage.
        //(3) To solve the same name issue, add time stamp to file name
        String fileName = dataInputStream.readUTF();
        long timestamp = System.currentTimeMillis();
        int index = fileName.lastIndexOf(".");
        String name = fileName.substring(0,index);
        String ext = fileName.substring(index);
        String serverFileName = name + timestamp + ext;

        //Create OutputStream to create file at specific path
        FileOutputStream fileOutputStream = new FileOutputStream( "upload/" + serverFileName);

        //(1) Create a path to save the file in current project
        //(2) Create a path to save the file in the server
        //(3)
        byte[] data = new byte[1024];
        int len;
        while( (len = dataInputStream.read(data) ) != -1 ){
            fileOutputStream.write(data, 0 , len);
        }

        //Feedback message for client
        OutputStream out = socket.getOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.println("File upload succeed!");
        
    }
}
