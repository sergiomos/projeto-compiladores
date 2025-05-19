package Sintatico;

public class Funcoes {
  private Parser parser;

  public Funcoes(Parser parser) {
    this.parser = parser;
  }

  protected boolean funcao(Node father) {
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

  private boolean parametros(Node father) {
    Node newFather = new Node("PARAMETROS");

    if (parametro(newFather)
        || (parametro(newFather)
            && parser.matcher.matchL(",", ", ", newFather) && parametros(newFather))) {
      father.addNode(newFather);
      return true;
    }

    return false;

  }

  private boolean parametro(Node father) {
    Node newFather = new Node("PARAMETRO");

    if (parser.elementos.tipo(newFather) && parser.elementos.id(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean argumentos(Node father) {
    Node newFather = new Node("ARGUMENTOS");

    if (parser.expressao.valor(newFather)
        && argumento(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean argumento(Node father) {
    Node newFather = new Node("ARGUMENTO");

    if ((parser.matcher.matchL(",", ", ", newFather) && argumentos(newFather)) || true) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }
}
