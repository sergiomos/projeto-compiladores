package Sintatico;

public class Expressao {
  private Parser parser;

  public Expressao(Parser parser) {
    this.parser = parser;
  }

  protected boolean expressao() {
    return operacao_matematica()
        || valor();
  }

  protected boolean expressao_teste() {
    return (parser.elementos.id() && expressaoL())
        || (parser.elementos.numero() && expressaoL())
        || (parser.elementos.boolean_valor() && expressaoL())
        || (parser.elementos.texto() && expressaoL());
  }

  protected boolean expressaoL() {
    return (parser.elementos.operadorArit() && expressao_teste() && expressaoL())
        || (parser.elementos.operadorRelacional() && expressao_teste() && expressaoL())
        || (parser.elementos.operadorLogico() && expressao_teste() && expressaoL())
        || true;
  }

  protected boolean operacao_matematica() {
    return termo()
        && expr_mat();
  }

  protected boolean expr_mat() {
    return (parser.matcher.matchL("+", "+")
        && termo()
        && expr_mat())
        || (parser.matcher.matchL("-", "-")
            && termo()
            && expr_mat())
        || true;
  }

  protected boolean termo() {
    return fator()
        && termo_linha();
  }

  protected boolean termo_linha() {
    return (parser.matcher.matchL("*", "*")
        && fator()
        && termo_linha())
        || ((parser.matcher.matchL("/", "/")
            && fator()
            && termo_linha()))
        || true;
  }

  protected boolean fator() {
    return parser.elementos.numero()
        || parser.elementos.id()
        || ((parser.matcher.matchL("(", "(")
            && operacao_matematica()
            && parser.matcher.matchL(")", ")")));
  }

  protected boolean valor() {
    return parser.elementos.numero()
        || parser.elementos.id()
        || parser.elementos.boolean_valor()
        || parser.elementos.texto();
  }

}
