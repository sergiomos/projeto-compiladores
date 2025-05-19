package Sintatico;

public class Condicionais {
  private Parser parser;

  public Condicionais(Parser parser) {
    this.parser = parser;
  }

  protected boolean se(Node father) {
    Node newFather = new Node("SE");

    if (parser.matcher.matchT("SE", "if ", newFather)
        && parser.matcher.matchL("(", "", newFather)
        && parser.expressao.expressaoLogica(newFather)
        && parser.matcher.matchL(")", "", newFather)
        && parser.matcher.matchL("{", "{\n", newFather)
        && parser.programa.bloco(newFather)
        && parser.matcher.matchL("}", "}", newFather)
        && seAux(newFather)) {

      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean seAux(Node father) {
    Node newFather = new Node("SE_AUX");

    if (senao(newFather) || (senaoSe(newFather) && seAux(newFather)) || true) {
      father.addNode(newFather);
      return true;
    }
    return false;
  }

  protected boolean senao(Node father) {
    Node newFather = new Node("SENAO");

    if (parser.matcher.matchT("SENAO", "else ", newFather)
        && parser.matcher.matchL("{", "{\n", newFather)
        && parser.programa.bloco(newFather)
        && parser.matcher.matchL("}", "}\n", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean senaoSe(Node father) {
    Node newFather = new Node("SENAOSE");

    if (parser.matcher.matchT("SENAOSE", "if ", newFather)
        && parser.matcher.matchL("(", "", newFather)
        && parser.expressao.expressaoLogica(newFather)
        && parser.matcher.matchL(")", "", newFather)
        && parser.matcher.matchL("{", "{\n", newFather)
        && parser.programa.bloco(newFather)
        && parser.matcher.matchL("}", "}", newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }
}
