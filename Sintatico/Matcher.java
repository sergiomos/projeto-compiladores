package Sintatico;

public class Matcher {
  private Parser parser;

  public Matcher(Parser parser) {
    this.parser = parser;
  }

  protected boolean matchL(String palavra, Node father) {
    if (parser.currentToken.getLexema().equals(palavra)) {
      Node newNode = new Node(parser.currentToken.getLexema());
      newNode.setLine(parser.currentToken.getLine());
      father.addNode(newNode);
      parser.currentToken = parser.getNextToken();
      return true;
    }
    return false;
  }

  protected boolean matchL(String palavra, String newCode, Node father) {
    if (matchL(palavra, father)) {
      traduz(newCode);
      return true;
    }
    return false;
  }

  protected boolean matchT(String type, Node father) {
    if (parser.currentToken.getType().equals(type)) {
      Node newNode = new Node(parser.currentToken.getLexema());
      newNode.setLine(parser.currentToken.getLine());
      father.addNode(newNode);
      parser.currentToken = parser.getNextToken();
      return true;
    }
    return false;
  }

  protected boolean matchT(String type, String newCode, Node father) {
    if (matchT(type, father)) {
      traduz(newCode);
      return true;
    }
    return false;
  }

  protected void traduz(String code) {
    System.out.print(code);
  }
}
