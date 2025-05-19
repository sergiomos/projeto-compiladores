package Sintatico;

public class Elementos {
  private Parser parser;

  public Elementos(Parser parser) {
    this.parser = parser;
  }

  protected boolean id(Node father) {
    Node newFather = new Node("IDENTIFICADOR");

    if (parser.matcher.matchT("IDENTIFICADOR", parser.currentToken.getLexema(), newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean fimDeLinha(Node father) {
    Node newFather = new Node("FIM_DE_LINHA");

    if (parser.matcher.matchL(";", "\n", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean numero(Node father) {
    Node newFather = new Node("NUMERO");

    if (parser.matcher.matchT("INT", parser.currentToken.getLexema(), newFather) ||
        parser.matcher.matchT("FLOAT", parser.currentToken.getLexema(), newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean tipo(Node father) {
    Node newFather = new Node("TIPO");

    if (parser.matcher.matchL("texto", newFather) ||
        parser.matcher.matchL("bool", newFather) ||
        parser.matcher.matchL("int", newFather) ||
        parser.matcher.matchL("dec", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean boolean_valor(Node father) {
    Node newFather = new Node("BOOLEAN_VALUE");

    if (parser.matcher.matchL("verdade", "true", newFather) ||
        parser.matcher.matchL("mentira", "false", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean texto(Node father) {
    Node newFather = new Node("STRING");

    if (parser.matcher.matchT("STRING", parser.currentToken.getLexema(), newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean operadorRelacional(Node father) {
    Node newFather = new Node("OPERADOR_RELACIONAL");

    if (parser.matcher.matchL("<", " < ", newFather) ||
        parser.matcher.matchL(">", " > ", newFather) ||
        parser.matcher.matchL("<=", " <= ", newFather) ||
        parser.matcher.matchL(">=", " >= ", newFather) ||
        parser.matcher.matchL("==", " == ", newFather) ||
        parser.matcher.matchL("!=", " != ", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean operadorLogico(Node father) {
    Node newFather = new Node("OPERADOR_LOGICO");

    if (parser.matcher.matchT("E_LOGICO", "&& ", newFather) ||
        parser.matcher.matchT("OU_LOGICO", "|| ", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean operadorAritmetico(Node father) {
    Node newFather = new Node("OPERADOR_ARITMETICO");

    if (parser.matcher.matchL("+", newFather) ||
        parser.matcher.matchL("-", newFather) ||
        parser.matcher.matchL("*", newFather) ||
        parser.matcher.matchL("/", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

}
