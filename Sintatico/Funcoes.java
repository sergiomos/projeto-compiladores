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
        && parametros()
        && parser.matcher.matchL(")", ")")
        && parser.matcher.matchL("{", "{\n")
        && parser.programa.bloco()
        && parser.matcher.matchL("}", "}");
  }

  private boolean parametros() {
    return parametro()
        || (parametro()
            && parser.matcher.matchL(",", ", ") && parametros());

  }

  private boolean parametro() {
    return (parser.elementos.tipo() && parser.elementos.id())
        || true;
  }

  protected boolean argumentos() {
    return parser.expressao.valor()
        && argumento() || true;
  }

  protected boolean argumento() {
    return (parser.matcher.matchL(",", ", ") && argumentos())
        || true;
  }
}
