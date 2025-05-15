package Sintatico;

public class Programa {
  private Parser parser;

  public Programa(Parser parser) {
    this.parser = parser;
  }

  protected boolean bloco() {
    return (comando() && bloco()) || true;
  }

  protected boolean comando() {
    return parser.variaveis.declaracao()
        || parser.variaveis.atribuicao()
        || parser.funcoes.funcao();
  }
}
