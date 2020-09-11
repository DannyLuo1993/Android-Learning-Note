import java.io.FileWriter;
import java.io.IOException;

public class TestFileGeneration {

    public void generateFile() throws IOException {

        FileWriter fileWriter = new FileWriter("src.txt");
        String str = "This is demo test of TestFileGeneration function";
        fileWriter.write(str);
        fileWriter.close();
    }
}
