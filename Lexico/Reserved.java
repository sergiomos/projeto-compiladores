package Lexico;

import java.text.CharacterIterator;

public class Reserved extends AFD {
  @Override
  public Token evaluate(CharacterIterator code) {
    String word = readWord(code);
    System.out.println(word);

    if (isTokenSeparator(code)) {
      switch (word) {
        case "se":
          return new Token("SE", word);
        case "senao":
          return new Token("SENAO", word);
        case "seNaoSe":
          return new Token("SENAOSE", word);
        case "enquanto":
          return new Token("ENQUANTO", word);
        case "para":
          return new Token("PARA", word);
        case "int":
          return new Token("INTEIRO", word);
        case "dec":
          return new Token("DECIMAL", word);
        case "bool":
          return new Token("BOOLEANO", word);
        case "texto":
          return new Token("TEXTO", word);
        case "def":
          return new Token("FUNCAO", word);
        case "verdade":
          return new Token("VERDADE", word);
        case "mentira":
          return new Token("MENTIRA", word);
        case "escreva":
          return new Token("ESCREVA", word);
        case "leia":
          return new Token("LEIA", word);
        case "e":
          return new Token("E_LOGICO", word);
        case "ou":
          return new Token("OU_LOGICO", word);
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
