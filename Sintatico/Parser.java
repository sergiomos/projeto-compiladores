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
    Condicionais condicionais;
    Reservado reservado;
    Lacos lacos;
    Tree tree;
    Node root;
        FirstFollowSets firstFollowSets;

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
        this.firstFollowSets = new FirstFollowSets();
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
                System.out.println("\nSintaticamente incorreta");
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
    
    // Helper methods for predictive parsing
    protected boolean isInFirstSet(String rule, String token) {
        switch (rule) {
            case "PROGRAMA":
                return FirstFollowSets.FIRST_PROGRAMA.contains(token);
            case "BLOCO":
                return FirstFollowSets.FIRST_BLOCO.contains(token);
            case "COMANDO":
                return FirstFollowSets.FIRST_COMANDO.contains(token);
            case "DECLARACAO":
                return FirstFollowSets.FIRST_DECLARACAO.contains(token);
            case "ATRIBUICAO":
                return FirstFollowSets.FIRST_ATRIBUICAO.contains(token);
            case "INCREMENTO":
                return FirstFollowSets.FIRST_INCREMENTO.contains(token);
            case "TIPO":
                return FirstFollowSets.FIRST_TIPO.contains(token);
            case "ID":
                return FirstFollowSets.FIRST_ID.contains(token);
            case "NUM":
                return FirstFollowSets.FIRST_NUM.contains(token);
            case "BOOLEAN":
                return FirstFollowSets.FIRST_BOOLEAN.contains(token);
            case "VALOR":
                return FirstFollowSets.FIRST_VALOR.contains(token);
            case "EXPRESSAO":
                return FirstFollowSets.FIRST_EXPRESSAO.contains(token);
            case "OPERACAO_MATEMATICA":
                return FirstFollowSets.FIRST_OPERACAO_MATEMATICA.contains(token);
            case "EXPR_MAT":
                return FirstFollowSets.FIRST_EXPR_MAT.contains(token);
            case "TERMO":
                return FirstFollowSets.FIRST_TERMO.contains(token);
            case "TERMO_LINHA":
                return FirstFollowSets.FIRST_TERMO_LINHA.contains(token);
            case "FATOR":
                return FirstFollowSets.FIRST_FATOR.contains(token);
            case "EXPRESSAO_LOGICA":
                return FirstFollowSets.FIRST_EXPRESSAO_LOGICA.contains(token);
            case "EXPRESSAO_LOGICA_L":
                return FirstFollowSets.FIRST_EXPRESSAO_LOGICA_L.contains(token);
            case "OPERADOR_ARITIMETICO":
                return FirstFollowSets.FIRST_OPERADOR_ARITIMETICO.contains(token);
            case "OPERADOR_RELACIONAL":
                return FirstFollowSets.FIRST_OPERADOR_RELACIONAL.contains(token);
            case "OPERADOR_LOGICO":
                return FirstFollowSets.FIRST_OPERADOR_LOGICO.contains(token);
            case "SE":
                return FirstFollowSets.FIRST_SE.contains(token);
            case "SE_AUX":
                return FirstFollowSets.FIRST_SE_AUX.contains(token);
            case "REPETICAO":
                return FirstFollowSets.FIRST_REPETICAO.contains(token);
            case "ENQUANTO":
                return FirstFollowSets.FIRST_ENQUANTO.contains(token);
            case "FUNCAO":
                return FirstFollowSets.FIRST_FUNCAO.contains(token);
            case "PARAMETROS":
                return FirstFollowSets.FIRST_PARAMETROS.contains(token);
            case "PARAMETRO":
                return FirstFollowSets.FIRST_PARAMETRO.contains(token);
            case "CHAMADA_FUNCAO":
                return FirstFollowSets.FIRST_CHAMADA_FUNCAO.contains(token);
            case "ARGUMENTOS":
                return FirstFollowSets.FIRST_ARGUMENTOS.contains(token);
            case "ARGUMENTO":
                return FirstFollowSets.FIRST_ARGUMENTO.contains(token);
            case "RETORNO":
                return FirstFollowSets.FIRST_RETORNO.contains(token);
            case "ESCREVA":
                return FirstFollowSets.FIRST_ESCREVA.contains(token);
            case "EXECUTE":
                return FirstFollowSets.FIRST_EXECUTE.contains(token);
            default:
                return false;
        }
    }
    
    protected boolean isInFollowSet(String rule, String token) {
        switch (rule) {
            case "PROGRAMA":
                return FirstFollowSets.FOLLOW_PROGRAMA.contains(token);
            case "BLOCO":
                return FirstFollowSets.FOLLOW_BLOCO.contains(token);
            case "COMANDO":
                return FirstFollowSets.FOLLOW_COMANDO.contains(token);
            case "DECLARACAO":
                return FirstFollowSets.FOLLOW_DECLARACAO.contains(token);
            case "ATRIBUICAO":
                return FirstFollowSets.FOLLOW_ATRIBUICAO.contains(token);
            case "INCREMENTO":
                return FirstFollowSets.FOLLOW_INCREMENTO.contains(token);
            case "TIPO":
                return FirstFollowSets.FOLLOW_TIPO.contains(token);
            case "ID":
                return FirstFollowSets.FOLLOW_ID.contains(token);
            case "NUM":
                return FirstFollowSets.FOLLOW_NUM.contains(token);
            case "BOOLEAN":
                return FirstFollowSets.FOLLOW_BOOLEAN.contains(token);
            case "VALOR":
                return FirstFollowSets.FOLLOW_VALOR.contains(token);
            case "EXPRESSAO":
                return FirstFollowSets.FOLLOW_EXPRESSAO.contains(token);
            case "OPERACAO_MATEMATICA":
                return FirstFollowSets.FOLLOW_OPERACAO_MATEMATICA.contains(token);
            case "EXPR_MAT":
                return FirstFollowSets.FOLLOW_EXPR_MAT.contains(token);
            case "TERMO":
                return FirstFollowSets.FOLLOW_TERMO.contains(token);
            case "TERMO_LINHA":
                return FirstFollowSets.FOLLOW_TERMO_LINHA.contains(token);
            case "FATOR":
                return FirstFollowSets.FOLLOW_FATOR.contains(token);
            case "EXPRESSAO_LOGICA":
                return FirstFollowSets.FOLLOW_EXPRESSAO_LOGICA.contains(token);
            case "EXPRESSAO_LOGICA_L":
                return FirstFollowSets.FOLLOW_EXPRESSAO_LOGICA_L.contains(token);
            case "OPERADOR_ARITIMETICO":
                return FirstFollowSets.FOLLOW_OPERADOR_ARITIMETICO.contains(token);
            case "OPERADOR_RELACIONAL":
                return FirstFollowSets.FOLLOW_OPERADOR_RELACIONAL.contains(token);
            case "OPERADOR_LOGICO":
                return FirstFollowSets.FOLLOW_OPERADOR_LOGICO.contains(token);
            case "SE":
                return FirstFollowSets.FOLLOW_SE.contains(token);
            case "SE_AUX":
                return FirstFollowSets.FOLLOW_SE_AUX.contains(token);
            case "REPETICAO":
                return FirstFollowSets.FOLLOW_REPETICAO.contains(token);
            case "ENQUANTO":
                return FirstFollowSets.FOLLOW_ENQUANTO.contains(token);
            case "FUNCAO":
                return FirstFollowSets.FOLLOW_FUNCAO.contains(token);
            case "PARAMETROS":
                return FirstFollowSets.FOLLOW_PARAMETROS.contains(token);
            case "PARAMETRO":
                return FirstFollowSets.FOLLOW_PARAMETRO.contains(token);
            case "CHAMADA_FUNCAO":
                return FirstFollowSets.FOLLOW_CHAMADA_FUNCAO.contains(token);
            case "ARGUMENTOS":
                return FirstFollowSets.FOLLOW_ARGUMENTOS.contains(token);
            case "ARGUMENTO":
                return FirstFollowSets.FOLLOW_ARGUMENTO.contains(token);
            case "RETORNO":
                return FirstFollowSets.FOLLOW_RETORNO.contains(token);
            case "ESCREVA":
                return FirstFollowSets.FOLLOW_ESCREVA.contains(token);
            case "EXECUTE":
                return FirstFollowSets.FOLLOW_EXECUTE.contains(token);
            default:
                return false;
        }
    }
}
