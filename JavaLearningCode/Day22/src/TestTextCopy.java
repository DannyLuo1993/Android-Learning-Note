import java.io.*;

public class TestTextCopy {

    public void copy(String srcFileName, String destFileName) throws IOException {

        FileReader fileReader = new FileReader("src.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("dest.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] str = new String[10];

    }
}
