package Lexico;

import java.text.CharacterIterator;

public class Symbols extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        System.out.println("SYM");
        switch (code.current()) {
            case '(':
                code.next();
                return new Token("ABRE_PARENTESES", "(");

            case ')':
                code.next();
                return new Token("FECHA_PARENTESES", ")");

            case ',':
                code.next();
                return new Token("VIRGULA", ",");

            case CharacterIterator.DONE:
                return new Token("EOF", "$");
            default:
                return null;
        }
    }
}
