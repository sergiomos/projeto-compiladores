
import java.util.List;
import java.io.IOException;
import Lexico.Lexer;
import Lexico.Token;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Token> tokens = null;
        String data = "2.2 + 34 + \"teste\" 9 \"sergiomos2010@gmail.com\" \"Hello World sa\" a variavel a1 _as a+b funcao()";
        Lexer lexer = new Lexer(data);

        tokens = lexer.getTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
