package Sintatico;

public class Elementos {
  private Parser parser;

  public Elementos(Parser parser) {
    this.parser = parser;
  }

  protected boolean id() {
    return parser.matcher.matchT("IDENTIFICADOR", parser.currentToken.getLexema());
  }

  protected boolean id(String newCode) {
    return parser.matcher.matchT("IDENTIFICADOR", newCode);
  }

  protected boolean fimDeLinha() {
    return parser.matcher.matchL(";", "\n");
  }

  protected boolean numero() {
    return parser.matcher.matchT("INT", parser.currentToken.getLexema()) ||
        parser.matcher.matchT("FLOAT", parser.currentToken.getLexema());
  }

  protected boolean tipo() {
    return parser.matcher.matchL("texto") ||
        parser.matcher.matchL("bool") ||
        parser.matcher.matchL("int") ||
        parser.matcher.matchL("dec");
  }

  protected boolean boolean_valor() {
    return parser.matcher.matchL("verdade", "true") ||
        parser.matcher.matchL("mentira", "false");
  }

  protected boolean texto() {
    return parser.matcher.matchT("STRING", parser.currentToken.getLexema());
  }

  protected boolean operadorRelacional() {
    return parser.matcher.matchL("<", "<") ||
        parser.matcher.matchL(">", ">") ||
        parser.matcher.matchL("<=", "<=") ||
        parser.matcher.matchL(">=", ">=") ||
        parser.matcher.matchL("==", "==") ||
        parser.matcher.matchL("!=", "!=");
  }

  protected boolean operadorLogico() {
    return parser.matcher.matchT("E_LOGICO", "&& ")
        || parser.matcher.matchT("OU_LOGICO", "|| ");
  }

}
