package Sintatico;

public class Lacos {
  private Parser parser;

  public Lacos(Parser parser) {
    this.parser = parser;
  }

  /*
   * para (i=0; i < 10; i++) {
   * bloco
   * }
   *
   *
   * for i in 0..10 {
   * }
   */

  protected boolean enquanto(Node father) {
    Node newFather = new Node("ENQUANTO");

    if (parser.matcher.matchT("ENQUANTO", "while ", newFather)
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
