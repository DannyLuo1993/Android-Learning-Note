import java.io.IOException;

public class MainActivity {

    public static void main(String[] args) throws IOException {

        DataIOStream dataIOStream = new DataIOStream();
        dataIOStream.testOutputStream();
        dataIOStream.testInputStream();
    }
}
