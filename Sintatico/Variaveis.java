package Sintatico;

public class Variaveis {
  private Parser parser;

  public Variaveis(Parser parser) {
    this.parser = parser;
  }

  protected boolean declaracao() {
    return parser.elementos.tipo() && parser.elementos.id() && parser.matcher.matchT("RECEBE", "= ")
        && parser.expressao.expressao() && parser.elementos.fimDeLinha();
  }

  protected boolean atribuicao() {
    return parser.elementos.id()
        && op_atrib()
        && parser.expressao.expressao()
        && parser.elementos.fimDeLinha();
  }

  private boolean op_atrib() {
    return parser.matcher.matchL("=", "= ") ||
        parser.matcher.matchL("+=", "+= ") ||
        parser.matcher.matchL("-=", "-= ");
  }
}
