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
        if (bloco()) {
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
        System.out.println("fn main() {");
    }

    private String footer() {
        return "}";
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

    private boolean bloco() {
        return comando() && bloco() || true;
    }

    private boolean comando() {
        return declaracao() ||
        atribuicao() ||
        funcao();
    }
    
    private boolean id() {
        return  matchT("IDENTIFICADOR", currentToken.getLexema() + " ");
    }

    private boolean fimDeLinha() {
        return  matchL(";", "\n");
    }
    // DECLARACAO -> TIPO ID "=" EXPRESSAO ";"
    private boolean declaracao() {
        return tipo() &&
        id() &&
        matchL("=", "= ") &&
        expressao() &&
        fimDeLinha();
    }
    
    // TIPO -> "txt" | "bool" | "int" | "dec"
    private boolean tipo() {
        if (matchL("texto", "let ") || 
            matchL("bool", "let ") || 
            matchL("int", "let ") || 
            matchL("dec", "let ")) {
            return true;
        }
        return false;
    }
    
    // EXPRESSAO -> EXPR_ARITMETICA | EXPR_LOGICA | VALOR
    private boolean expressao() {
        return expr_aritmetica() || expr_logica() || valor();
    }
    
    // VALOR -> ID | NUM | BOOLEAN
    private boolean valor() {
        return matchT("IDENTIFICADOR", currentToken.getLexema()) || 
            matchT("INT", currentToken.getLexema()) || 
            matchT("FLOAT", currentToken.getLexema()) || 
            matchT("STRING", currentToken.getLexema()) || 
            boolean_valor();
    }
    
    // BOOLEAN -> "verdade" | "mentira"
    private boolean boolean_valor() {
        return matchL("verdade", "true") || 
        matchL("mentira", "false");
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

    private boolean op_atrib() {
        return matchL("=", "= ") ||
        matchL("+=", "+= ") ||
        matchL("-=", "-= ");
    }
    private boolean atribuicao() {
        return id() && op_atrib() && expressao() && fimDeLinha();
    }

    private boolean funcao() {
        return matchT("FUNCAO", "fn ") && id() && matchL("()")
    }
}
