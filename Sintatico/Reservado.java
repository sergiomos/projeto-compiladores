package Sintatico;

public class Reservado {
  private Parser parser;

  public Reservado(Parser parser) {
    this.parser = parser;
  }

  protected boolean evaluate() {
    return escreva() || leia();
  }

  protected boolean escreva() {
    return parser.matcher.matchT("ESCREVA", "println!")
        && parser.matcher.matchL("(", "(")
        && parser.funcoes.argumentos()
        && parser.matcher.matchL(")", ")")
        && parser.elementos.fimDeLinha();
  }

  protected boolean leia() {
    return parser.matcher.matchT("LEIA", "std::io::stdin().read_line")
        && parser.matcher.matchL("(", "(")
        && parser.elementos.id("&mut")
        && parser.matcher.matchL(")", ")")
        && parser.matcher.matchL(";", ".expect(\"Failed to read line\");\n")
        && parser.elementos.fimDeLinha();
  }

}
