package Sintatico;

public class Funcoes {
  private Parser parser;

  public Funcoes(Parser parser) {
    this.parser = parser;
  }

  protected boolean funcao(Node father) {
    if (!parser.isInFirstSet("FUNCAO", parser.currentToken.getType())) {
      return false;
    }

    Node newFather = new Node("FUNCAO");
    if (parser.matcher.matchT("FUNCAO", "fn ", newFather)
        && parser.elementos.id(newFather)
        && parser.matcher.matchL("(", "(", newFather)
        && parametros(newFather)
        && parser.matcher.matchL(")", ")", newFather)
        && parser.matcher.matchL("{", "{\n", newFather)
        && parser.programa.bloco(newFather)
        && parser.matcher.matchL("}", "}", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean chamadaFuncao(Node father) {
    if (!parser.isInFirstSet("CHAMADA_FUNCAO", parser.currentToken.getType())) {
      return false;
    }

    Node newFather = new Node("CHAMADA_FUNCAO");
    if (parser.elementos.id(newFather)
        && parser.matcher.matchL("(", "(", newFather)
        && argumentos(newFather)
        && parser.matcher.matchL(")", ")", newFather)
        && parser.matcher.matchL(";", ";\n", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  private boolean parametros(Node father) {
    Node newFather = new Node("PARAMETROS");

    if (parser.matcher.matchL(")", ")", father)) {
      // Empty parameter list
      father.addNode(newFather);
      return true;
    }

    if (parser.elementos.id(newFather)) {
      if (parametro(newFather)) {
        father.addNode(newFather);
        return true;
      }
    }

    return false;
  }

  private boolean parametro(Node father) {
    if (!parser.matcher.matchL(",", ", ", father)) {
      return true; // End of parameter list
    }

    Node newFather = new Node("PARAMETRO");
    if (parser.elementos.id(newFather)) {
      father.addNode(newFather);
      return parametro(newFather);
    }

    return false;
  }

  protected boolean argumentos(Node father) {
    Node newFather = new Node("ARGUMENTOS");

    if (parser.matcher.matchL(")", ")", father)) {
      // Empty argument list
      father.addNode(newFather);
      return true;
    }

    if (parser.expressao.valor(newFather)) {
      if (argumento(newFather)) {
        father.addNode(newFather);
        return true;
      }
    }

    return false;
  }

  protected boolean argumento(Node father) {
    if (!parser.matcher.matchL(",", ", ", father)) {
      return true; // End of argument list
    }

    Node newFather = new Node("ARGUMENTO");
    if (parser.expressao.valor(newFather)) {
      father.addNode(newFather);
      return argumento(newFather);
    }

    return false;
  }
}
