
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import Lexico.Lexer;
import Lexico.Token;
import Sintatico.Parser;
import Sintatico.Tree;
import Semantico.Analisador;

public class Main {
    public static void main(String[] args) throws IOException {
        String data = readFile();
        Lexer lexer = new Lexer(data);
        List<Token> tokens = lexer.getTokens();

        System.out.println("--------------- ANÁLISE LEXICA ---------------");

        for (Token i : tokens) {

            System.out.println(i);
        }

        System.out.println("--------------- ANÁLISE SINTÁTICA ---------------");

        Parser parser = new Parser(tokens);
        Tree tree = parser.parse();

        if (tree != null) {
            System.out.println("--------------- ÁRVORE DE DERIVAÇÃO ---------------");
            tree.printTree();
        }

        // Executa análise semântica

        System.out.println("--------------- ANÁLISE SEMÂNTICA ---------------");
        Analisador semanticAnalyzer = new Analisador(tree);
        if (semanticAnalyzer.run()) {
            System.out.println("\nAnálise semântica concluída com sucesso.");
        } else {
            semanticAnalyzer.printErrors();
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
