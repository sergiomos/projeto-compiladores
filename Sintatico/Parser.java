package Sintatico;

import java.util.List;

import javax.management.RuntimeErrorException;

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
    Condicionais condicionais;
    Reservado reservado;
    Lacos lacos;
    Tree tree;
    Node root;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.matcher = new Matcher(this);
        this.programa = new Programa(this);
        this.variaveis = new Variaveis(this);
        this.elementos = new Elementos(this);
        this.expressao = new Expressao(this);
        this.funcoes = new Funcoes(this);
        this.condicionais = new Condicionais(this);
        this.reservado = new Reservado(this);
        this.lacos = new Lacos(this);
        this.root = new Node("Raiz");
        this.tree = new Tree(root);
    }

    public Tree parse() {
        currentToken = getNextToken();
        header();
        if (programa.bloco(root)) {
            if (currentToken.getType() == "EOF") {
                footer();
                System.out.println("\nSintaticamente correta");
                return tree;
            } else {
                System.out.println("\nSintaticamente incorreta ");
                return null;
            }
        }
        return null;
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

    private void footer() {
        System.out.println("}");
    }

    public String peekToken() {
        return matcher.toString();
    }
}
