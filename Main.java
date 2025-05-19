
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
        data = "int idade = 25; dec altura = 1.75; texto nome = \"João\"; bool ativo = verdade; idade += 5;contador++;int resultado = (10 + 5) * 2;dec media = (nota1 + nota2) / 2;int total = valor1 * valor2 + valor3;se (idade >= 18) {    texto status = \"maior de idade\";} senaose (idade >= 12) {    texto status = \"adolescente\";} senao {    texto status = \"criança\";}enquanto (contador < 10) {    contador++;}leia(a);escreva(\"a\", \"b\", c);def teste() {  int b = 2;}";
                        
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
