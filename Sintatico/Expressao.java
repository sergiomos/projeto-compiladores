package Sintatico;

public class Expressao {
  private Parser parser;

  public Expressao(Parser parser) {
    this.parser = parser;
  }

  protected boolean expressao(Node father) {
    if (!parser.isInFirstSet("EXPRESSAO", parser.currentToken.getType())) {
      return false;
    }

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
    if (!parser.isInFirstSet("EXPRESSAO_LOGICA", parser.currentToken.getType())) {
      return false;
    }

    Node newFather = new Node("EXPRESSAO_LOGICA");

    // Handle parenthesized expressions
    if (parser.matcher.matchL("(", "(", newFather)) {
      if (expressaoLogica(newFather) && parser.matcher.matchL(")", ")", newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Handle simple expressions
    if (valor(newFather) && expressaoLogicaL(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean expressaoLogicaL(Node father) {
    Node newFather = new Node("EXPRESSAO_LOGICA_L");

    // Check for operators
    if (parser.elementos.operadorAritmetico(newFather) 
        || parser.elementos.operadorRelacional(newFather)
        || parser.elementos.operadorLogico(newFather)) {
      if (expressaoLogica(newFather) && expressaoLogicaL(newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Empty expression (epsilon)
    father.addNode(newFather);
    return true;
  }

  protected boolean operacao_matematica(Node father) {
    if (!parser.isInFirstSet("OPERACAO_MATEMATICA", parser.currentToken.getType())) {
      return false;
    }

    Node newFather = new Node("OPERACAO_MAT");
    if (termo(newFather) && expr_mat(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean expr_mat(Node father) {
    Node newFather = new Node("EXPR_MAT");

    // Check for addition
    if (parser.matcher.matchL("+", "+", newFather)) {
      if (termo(newFather) && expr_mat(newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Check for subtraction
    if (parser.matcher.matchL("-", "-", newFather)) {
      if (termo(newFather) && expr_mat(newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Empty expression (epsilon)
    father.addNode(newFather);
    return true;
  }

  protected boolean termo(Node father) {
    if (!parser.isInFirstSet("TERMO", parser.currentToken.getType())) {
      return false;
    }

    Node newFather = new Node("TERMO");
    if (fator(newFather) && termo_linha(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean termo_linha(Node father) {
    Node newFather = new Node("TERMO_LINHA");

    // Check for multiplication
    if (parser.matcher.matchL("*", "*", newFather)) {
      if (fator(newFather) && termo_linha(newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Check for division
    if (parser.matcher.matchL("/", "/", newFather)) {
      if (fator(newFather) && termo_linha(newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Empty expression (epsilon)
    father.addNode(newFather);
    return true;
  }

  protected boolean fator(Node father) {
    if (!parser.isInFirstSet("FATOR", parser.currentToken.getType())) {
      return false;
    }

    Node newFather = new Node("FATOR");

    // Handle parenthesized expressions
    if (parser.matcher.matchL("(", "(", newFather)) {
      if (operacao_matematica(newFather) && parser.matcher.matchL(")", ")", newFather)) {
        father.addNode(newFather);
        return true;
      }
      return false;
    }

    // Handle simple values
    if (parser.elementos.numero(newFather) || parser.elementos.id(newFather)) {
      father.addNode(newFather);
      return true;
    }

    return false;
  }

  protected boolean valor(Node father) {
    if (!parser.isInFirstSet("VALOR", parser.currentToken.getType())) {
      return false;
    }

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
