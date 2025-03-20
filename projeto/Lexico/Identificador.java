package Lexico;

import java.text.CharacterIterator;

public class Identificador extends AFD {
  @Override
  public Token evaluate(CharacterIterator code) {
    System.out.println("ID");
    String id = readID(code);

    if (isTokenSeparator(code)) {
      return new Token("IDENTIFICADOR", id);
    }

    return null;
  }

  public String readID(CharacterIterator code) {

    if (isValidIDPrefix(code)) {
      String id = "";
      while (Character.isLetterOrDigit(code.current()) || code.current() == '_') {
        id += code.current();
        code.next();
      }

      return id;
    }

    return null;
  }

  public boolean isValidIDPrefix(CharacterIterator code) {
    return Character.isLetter(code.current()) || code.current() == '_';
  }
}
