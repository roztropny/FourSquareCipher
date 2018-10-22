import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    static String readFile(String path) throws IOException {
        return readFile(path, Charset.defaultCharset());
    }

    static String readFile(String path, Charset encoding) throws IOException{
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    static void writeFile(String path, String content) throws FileNotFoundException, UnsupportedEncodingException {
        //PrintWriter writer = new PrintWriter(path, Charset.defaultCharset().toString());
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        writer.println(content);
        writer.close();
    }
}
