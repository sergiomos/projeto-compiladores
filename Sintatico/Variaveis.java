package Sintatico;


public class Variaveis {
  private Parser parser;

  public Variaveis(Parser parser) {
    this.parser = parser;
  }

  protected boolean declaracao(Node father) {
    Node newFather = new Node("DECLARACAO");
    if(parser.isInFirstSet("DECLARACAO", parser.currentToken.getType())){

      parser.matcher.traduz("let ");
      
      if (parser.elementos.tipo(newFather)
      && parser.elementos.id(newFather)
      && parser.matcher.matchT("RECEBE", "= ", newFather)
      && parser.expressao.expressao(newFather)
      && parser.elementos.fimDeLinha(newFather)
    ) {
        
          father.addNode(newFather);
          return true;
      }

      parser.error("declaraçao invalida -> " + parser.currentToken.getLexema());
    }

    return false;
  }

  protected boolean atribuicao(Node father) {
    Node newFather = new Node("ATRIBUICAO");

    if(parser.isInFirstSet("ATRIBUICAO", parser.currentToken.getType())){
      if (parser.elementos.id(newFather)
          && aux(newFather)
          && parser.isInFollowSet("ATRIBUICAO", parser.currentToken.getType())) {
        father.addNode(newFather);
        return true;
      }

      parser.error("atribuição invalida -> " + parser.currentToken.getLexema());
    }

    return false;
  }

  private boolean op_atrib(Node father) {
    Node newFather = new Node("OP_ATRIB");

      if ((parser.matcher.matchL("=", "= ", newFather) ||
          parser.matcher.matchL("+=", "+= ", newFather) ||
          parser.matcher.matchL("-=", "-= ", newFather))
         ) {
        father.addNode(newFather);
        return true;
    }

    return false;
  }

  protected boolean atribuicao_aux(Node father) {
    Node newFather = new Node("ATRIBUICAO");

    if (op_atrib(newFather)
        && parser.expressao.expressao(newFather)
        && parser.elementos.fimDeLinha(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean incremento(Node father) {
    Node newFather = new Node("INCREMENTO");

    if(parser.isInFirstSet("INCREMENTO", parser.currentToken.getType())){
      
      if (((parser.matcher.matchT("INCREMENTO", " += 1", newFather)
        && parser.elementos.fimDeLinha(newFather)) ||
        (parser.matcher.matchT("DECREMENTO", " -= 1", newFather)
            && parser.elementos.fimDeLinha(newFather)))
            && parser.isInFollowSet("INCREMENTO", parser.currentToken.getType())) {
        father.addNode(newFather);
        return true;
      }

      parser.error("incremento invalido -> " + parser.currentToken.getLexema());
    }

    return false;
  }

  protected boolean aux(Node father) {
    return incremento(father) || atribuicao_aux(father);
  }
}
