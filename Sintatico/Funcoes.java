package Sintatico;

public class Funcoes {
  private Parser parser;

  public Funcoes(Parser parser) {
    this.parser = parser;
  }

  protected boolean funcao() {
    return parser.matcher.matchT("FUNCAO", "fn ")
        && parser.elementos.id()
        && parser.matcher.matchL("(", "(")
        && parser.matcher.matchL(")", ")");
  }
}
