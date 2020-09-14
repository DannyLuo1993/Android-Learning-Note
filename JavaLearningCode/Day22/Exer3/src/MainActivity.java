import java.io.IOException;

public class MainActivity {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        TestSerializable testSerializable = new TestSerializable();
        testSerializable.testOutputStream();
        testSerializable.testInputStream();

    }
}
