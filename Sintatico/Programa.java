package Sintatico;

public class Programa {
  private Parser parser;

  public Programa(Parser parser) {
    this.parser = parser;
  }

  protected boolean bloco(Node father) {
    Node newFather = new Node("BLOCO");

    // Check if we have a command
    if (parser.isInFirstSet("COMANDO", parser.currentToken.getType())) {
      if (comando(newFather) && bloco(newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Empty block (epsilon)
    father.addNode(newFather);
    return true;
  }

  protected boolean comando(Node father) {
    Node newFather = new Node("COMANDO");

    if (parser.variaveis.declaracao(newFather)
        || parser.variaveis.atribuicao(newFather)
        || parser.funcoes.funcao(newFather)
        || parser.funcoes.chamadaFuncao(newFather)
        || parser.condicionais.se(newFather)
        || parser.reservado.evaluate(newFather)
        || parser.lacos.enquanto(newFather)
        || parser.lacos.doWhile(newFather)
        || parser.variaveis.incremento(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }
}
