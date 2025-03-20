package Lexico;

import java.text.CharacterIterator;

public class Reservado extends AFD {
  @Override
  public Token evaluate(CharacterIterator code) {
    System.out.println("RESERVADO");
    String word = readWord(code);

    if (isTokenSeparator(code)) {
      String type;
      switch (word) {
        case "se":
          return new Token("CONDICAO_SE", word);
        case "seNao":
          return new Token("CONDICAO_SENAO", word);
        case "enquanto":
          return new Token("LOOP_ENQUANTO", word);
        case "para":
          return new Token("LOOP_PARA", word);

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
