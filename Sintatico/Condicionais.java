package Sintatico;

public class Condicionais {
  private Parser parser;

  public Condicionais(Parser parser) {
    this.parser = parser;
  }

  protected boolean se() {
    return parser.matcher.matchT("SE", "if ")
        && parser.matcher.matchL("(", "(")
        && parser.matcher.matchL(")", ")")
        && parser.matcher.matchL("{", "{\n")
        && parser.programa.bloco()
        && parser.matcher.matchL("}", "}")
        && seAux();
  }

  protected boolean seAux() {
    return senao() || (senaoSe() && seAux()) || true;
  }

  protected boolean senao() {
    return parser.matcher.matchT("SENAO", "else ")
        && parser.matcher.matchL("{", "{\n")
        && parser.programa.bloco()
        && parser.matcher.matchL("}", "}");
  }

  protected boolean senaoSe() {
    return parser.matcher.matchT("SENAOSE", "else if ")
        && parser.matcher.matchL("(", "(")
        && parser.matcher.matchL(")", ")")
        && parser.matcher.matchL("{", "{\n")
        && parser.programa.bloco()
        && parser.matcher.matchL("}", "}");
  }
}
