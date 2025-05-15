package Sintatico;

public class Expressao {
  private Parser parser;

  public Expressao(Parser parser) {
    this.parser = parser;
  }

  protected boolean expressao() {
    return expr_aritmetica() || expr_logica() || valor();
  }

  protected boolean expr_aritmetica() {
    return parser.elementos.numero() || parser.elementos.id();
  }

  protected boolean expr_logica() {
    return parser.elementos.boolean_valor()
        || parser.elementos.id()
        || parser.elementos.numero();
  }

  protected boolean valor() {
    return parser.elementos.numero()
        || parser.elementos.id()
        || parser.elementos.boolean_valor()
        || parser.elementos.texto();
  }

}
