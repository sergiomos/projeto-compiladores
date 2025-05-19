package Sintatico;

public class Expressao {
  private Parser parser;

  public Expressao(Parser parser) {
    this.parser = parser;
  }

  protected boolean expressao(Node father) {
    Node newFather = new Node("EXPRESSAO");

    if (operacao_matematica(newFather)
        || expressaoLogica(newFather)
        || valor(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean expressaoLogica(Node father) {
    Node newFather = new Node("EXPRESSAO_LOGICA");

    if ((parser.elementos.id(newFather) && expressaoLogicaL(newFather))
        || (parser.elementos.numero(newFather) && expressaoLogicaL(newFather))
        || (parser.elementos.boolean_valor(newFather) && expressaoLogicaL(newFather))
        || (parser.elementos.texto(newFather) && expressaoLogicaL(newFather))
        || (parser.matcher.matchL("(", "(", newFather) && expressaoLogica(newFather)
            && parser.matcher.matchL(")", ")", newFather))) {

      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean expressaoLogicaL(Node father) {
    Node newFather = new Node("EXPRESSAO_LOGICA_L");

    if ((parser.elementos.operadorAritmetico(newFather) && expressaoLogica(newFather) && expressaoLogicaL(newFather))
        || (parser.elementos.operadorRelacional(newFather) && expressaoLogica(newFather) && expressaoLogicaL(newFather))
        || (parser.elementos.operadorLogico(newFather) && expressaoLogica(newFather) && expressaoLogicaL(newFather))
        || true) {
      father.addNode(newFather);

      return true;
    }

    return false;
  }

  protected boolean operacao_matematica(Node father) {
    Node newFather = new Node("OPERACAO_MAT");

    if (termo(newFather)
        && expr_mat(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean expr_mat(Node father) {
    Node newFather = new Node("EXPR_MAT");

    if ((parser.matcher.matchL("+", "+", newFather)
        && termo(newFather)
        && expr_mat(newFather))
        || (parser.matcher.matchL("-", "-", newFather)
            && termo(newFather)
            && expr_mat(newFather))
        || true) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean termo(Node father) {
    Node newFather = new Node("TERMO");

    if (fator(newFather)
        && termo_linha(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean termo_linha(Node father) {
    Node newFather = new Node("TERMO_LINHA");

    if ((parser.matcher.matchL("*", "*", newFather)
        && fator(newFather)
        && termo_linha(newFather))
        || ((parser.matcher.matchL("/", "/", newFather)
            && fator(newFather)
            && termo_linha(newFather)))
        || true) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean fator(Node father) {
    Node newFather = new Node("FATOR");

    if (parser.elementos.numero(newFather)
        || parser.elementos.id(newFather)
        || ((parser.matcher.matchL("(", "(", newFather)
            && operacao_matematica(newFather)
            && parser.matcher.matchL(")", ")", newFather)))) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean valor(Node father) {
    Node newFather = new Node("VALOR");

    if (parser.elementos.numero(newFather)
        || parser.elementos.id(newFather)
        || parser.elementos.boolean_valor(newFather)
        || parser.elementos.texto(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

}
