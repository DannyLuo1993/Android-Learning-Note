import java.io.IOException;

public class MainActivity {

    public static void main(String[] args) throws IOException {

        TestTextCopy testTextCopy = new TestTextCopy();
        TestFileGeneration testFileGeneration = new TestFileGeneration();
        testFileGeneration.generateFile();
        testTextCopy.copy("src.txt", "dest.txt");

    }
}
