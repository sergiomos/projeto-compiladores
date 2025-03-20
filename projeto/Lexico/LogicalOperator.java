package Lexico;

import java.text.CharacterIterator;
import java.util.ArrayList;

public class LogicalOperator extends AFD {

  @Override
  public Token evaluate(CharacterIterator code) {

    System.out.println("OPERADOR LOGICO");

    String operator = readOperador(code);

    switch (operator) {
      case "&&":
        return new Token("E_LOGICO", operator);

      case "||":
        return new Token("OU_LOGICO", operator);

      case "!":
        return new Token("NAO_LOGICO", operator);

      default:
        return null;
    }

  }

  public String readOperador(CharacterIterator code) {
    String operator = "";

    while (isOperator(code)) {
      operator += code.current();
      code.next();
    }

    return operator;
  }

  public boolean isOperator(CharacterIterator code) {
    ArrayList<Character> operators = new ArrayList<Character>();
    operators.add('&');
    operators.add('|');
    operators.add('!');

    return operators.contains(code.current());
  }
}
