
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import Lexico.Lexer;
import Lexico.Token;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Token> tokens = null;
        String data = readFile();

        System.out.println(data);
        Lexer lexer = new Lexer(data);

        tokens = lexer.getTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public static String readFile() throws FileNotFoundException, IOException {
        String fileName = "codigo.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String everything = sb.toString();

            return everything;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            br.close();
        }
    }
}
