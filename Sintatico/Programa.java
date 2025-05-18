package Sintatico;

public class Programa {
  private Parser parser;

  public Programa(Parser parser) {
    this.parser = parser;
  }

  protected boolean bloco(Node father) {
    Node newFather = new Node("BLOCO");

    if ((comando(newFather) && bloco(newFather)) || true) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean comando(Node father) {
    Node newFather = new Node("COMANDO");

    if (parser.variaveis.declaracao(newFather)
        || parser.variaveis.atribuicao(newFather)
        || parser.funcoes.funcao(newFather)
        || parser.condicionais.se(newFather)
        || parser.reservado.evaluate(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }
}
