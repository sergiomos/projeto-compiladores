package Sintatico;

public class Matcher {
  private Parser parser;

  public Matcher(Parser parser) {
    this.parser = parser;
  }

  protected boolean matchL(String palavra) {
    if (parser.currentToken.getLexema().equals(palavra)) {
      parser.currentToken = parser.getNextToken();
      return true;
    }

    return false;
  }

  protected boolean matchL(String palavra, String newCode) {
    if (matchL(palavra)) {
      traduz(newCode);
      return true;
    }

    return false;
  }

  protected boolean matchT(String type) {
    if (parser.currentToken.getType().equals(type)) {
      parser.currentToken = parser.getNextToken();
      return true;
    }

    return false;
  }

  protected boolean matchT(String type, String newCode) {
    if (matchT(type)) {
      traduz(newCode);
      return true;
    }

    return false;
  }

  private void traduz(String code) {
    System.out.print(code);
  }
}
