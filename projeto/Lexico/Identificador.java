package Lexico;

import java.text.CharacterIterator;

public class Identificador extends AFD {
  @Override
  public Token evaluate(CharacterIterator code) {
    System.out.println("ID");
    String id = readID(code);

    if (id == null)
      return null;

    return new Token("IDENTIFICADOR", id);
  }

  public String readID(CharacterIterator code) {

    if (Character.isLetter(code.current()) || code.current() == '_') {
      String id = "";

      while (Character.isLetterOrDigit(code.current()) || code.current() == '_') {
        if (isTokenSeparator(code))
          break;

        id += code.current();
        code.next();
      }

      return id;
    }

    return null;

  }
}
