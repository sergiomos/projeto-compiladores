package Sintatico;

public class Variaveis {
  private Parser parser;

  public Variaveis(Parser parser) {
    this.parser = parser;
  }

  protected boolean declaracao(Node father) {
    Node newFather = new Node("DECLARACAO");

    if (parser.elementos.tipo(newFather)
        && parser.elementos.id(newFather)
        && parser.matcher.matchT("RECEBE", "= ", newFather)
        && parser.expressao.expressao(newFather)
        && parser.elementos.fimDeLinha(newFather)) {

      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean atribuicao(Node father) {
    Node newFather = new Node("ATRIBUICAO");

    if (parser.elementos.id(newFather)
        && op_atrib(newFather)
        && parser.expressao.expressao(newFather)
        && parser.elementos.fimDeLinha(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  private boolean op_atrib(Node father) {
    Node newFather = new Node("OP_ATRIB");

    if (parser.matcher.matchL("=", "= ", newFather) ||
        parser.matcher.matchL("+=", "+= ", newFather) ||
        parser.matcher.matchL("-=", "-= ", newFather)) {
      father.addNode(newFather);

      return true;
    }

    return false;
  }
}
