package Sintatico;

public class Reservado {
  private Parser parser;

  public Reservado(Parser parser) {
    this.parser = parser;
  }

  protected boolean evaluate(Node father) {
    return escreva(father) || leia(father);
  }

  protected boolean escreva(Node father) {
    Node newFather = new Node("ESCREVA");

    if (parser.matcher.matchT("ESCREVA", "println!", newFather)
        && parser.matcher.matchL("(", "(\"{}\",", newFather)
        && parser.funcoes.argumentos(newFather)
        && parser.matcher.matchL(")", ")", newFather)
        && parser.elementos.fimDeLinha(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean leia(Node father) {
    Node newFather = new Node("LEIA");

    if (parser.isInFirstSet("LEIA", parser.currentToken.getType())) {
      if (parser.matcher.matchT("LEIA", "std::io::stdin().read_line", newFather)
          && parser.matcher.matchL("(", "(", newFather)
          && parser.elementos.id(newFather, "&" + parser.currentToken.getLexema())
          && parser.matcher.matchL(")", ")", newFather)
          && parser.matcher.matchL(";", ".expect(\"Failed to read line\");\n", newFather)) {
        father.addNode(newFather);
        return true;
      }

      parser.error("leia invalido -> " + parser.currentToken.getLexema());
    }

    return false;
  }

}
