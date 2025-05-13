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
        if (declaracao()) {
            if(matchT("EOF", footer())){
                System.out.println("\nSintaticamente correta");
                return;
            } else {
                System.out.println("\nSintaticamente incorreta");
            
        }
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
    
    // DECLARACAO -> TIPO ID "=" EXPRESSAO ";"
    private boolean declaracao() {
        return tipo() &&
        matchT("IDENTIFICADOR", currentToken.getLexema() + " ") &&
        matchL("=", "= ") &&
        expressao() &&
        matchL(";", ";\n");
    }
    
    // TIPO -> "txt" | "bool" | "int" | "dec"
    private boolean tipo() {
        if (matchL("txt", "String ") || 
            matchL("bool", "boolean ") || 
            matchL("int", "int ") || 
            matchL("dec", "double ")) {
            return true;
        }
        return false;
    }
    
    // EXPRESSAO -> EXPR_ARITMETICA | EXPR_LOGICA | VALOR
    private boolean expressao() {
        return expr_aritmetica() || expr_logica() || valor()
    }
    
    // VALOR -> ID | NUM | BOOLEAN
    private boolean valor() {
        return matchT("IDENTIFICADOR", currentToken.getLexema()) || 
            matchT("INT", currentToken.getLexema()) || 
            matchT("DEC", currentToken.getLexema()) || 
            boolean_valor()
    }
    
    // BOOLEAN -> "verdade" | "mentira"
    private boolean boolean_valor() {
        return matchL("verdade", "true") || 
        matchL("mentira", "false")
    }
    
    // Placeholder methods for other expression types
    // These would need to be implemented based on the grammar
    private boolean expr_aritmetica() {
        // Implementation for arithmetic expressions
        // For now, just check if it's a value
        return valor();
    }
    
    private boolean expr_logica() {
        // Implementation for logical expressions
        // For now, just check if it's a boolean value
        return boolean_valor();
    }
}
