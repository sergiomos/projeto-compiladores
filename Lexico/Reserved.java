package Lexico;

import java.text.CharacterIterator;

public class Reserved extends AFD {
  @Override
  public Token evaluate(CharacterIterator code) {
    String word = readWord(code);

    if (isTokenSeparator(code)) {
      switch (word) {
        case "se":
          return new Token("SE", word);
        case "senao":
          return new Token("SENAO", word);
        case "enquanto":
          return new Token("ENQUANTO", word);
        case "para":
          return new Token("PARA", word);
        default:
          return null;
      }
    }

    return null;
  }

  public String readWord(CharacterIterator code) {
    String word = "";

    while (Character.isLetter(code.current())) {
      word += code.current();
      code.next();
    }

    return word;
  }
}
