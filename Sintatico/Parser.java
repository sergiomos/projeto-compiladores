package Sintatico;

import java.util.List;
import Lexico.Token;

public class Parser {
    List<Token> tokens;
    Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parser() {
        currentToken = getNextToken();
        header();
        if(matchT("EOF", footer())){
            System.out.println("\nSintaticamente correta");
            return;
        } else {
            System.out.println("\nSintaticamente incorreta");
        }
    }

    private Token getNextToken() {
        if(tokens.size() > 0){
            return tokens.remove(0);
        }
        return null;
    }

    private void header() {
        System.out.println("Public class Code{");
        System.out.println("public static void main(String[]args){");
    }

    private String footer() {
        return "}\n}";
    }

    private boolean matchL(String palavra, String newCode) {
        if (currentToken.getLexema().equals(palavra)) {
            traduz(newCode);
            currentToken = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchT(String palavra, String newCode) {
        if (currentToken.getType().equals(palavra)) {
            traduz(newCode);
            currentToken = getNextToken();
            return true;
        }
        return false;
    }

    private void traduz(String code) {
        System.out.print(code);
    }
}
