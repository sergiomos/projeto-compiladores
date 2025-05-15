package Sintatico;

import java.util.List;
import Lexico.Token;

public class Parser {
    List<Token> tokens;
    Matcher matcher;
    Token currentToken;
    Programa programa;
    Variaveis variaveis;
    Elementos elementos;
    Expressao expressao;
    Funcoes funcoes;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.matcher = new Matcher(this);
        this.programa = new Programa(this);
        this.variaveis = new Variaveis(this);
        this.elementos = new Elementos(this);
        this.expressao = new Expressao(this);
        this.funcoes = new Funcoes(this);
    }

    public void parse() {
        currentToken = getNextToken();
        header();
        if (programa.bloco()) {
            if (matcher.matchT("EOF", footer())) {
                System.out.println("\nSintaticamente correta");
                return;
            } else {
                System.out.println("\nSintaticamente incorreta");

            }
        }
    }

    protected Token getNextToken() {
        if (tokens.size() > 0) {
            return tokens.remove(0);
        }
        return null;
    }

    private void header() {
        System.out.println("fn main() {");
    }

    private String footer() {
        return "}";
    }
}
