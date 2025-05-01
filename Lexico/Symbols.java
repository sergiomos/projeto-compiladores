package Lexico;

import java.text.CharacterIterator;

public class Symbols extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        switch (code.current()) {
            case '(':
                code.next();
                return new Token("ABRE_PARENTESES", "(");

            case ')':
                code.next();
                return new Token("FECHA_PARENTESES", ")");

            case '{':
                code.next();
                return new Token("ABRE_CHAVE", "{");

            case '}':
                code.next();
                return new Token("FECHA_CHAVE", "}");

            case '[':
                code.next();
                return new Token("ABRE_COLCHETE", "[");

            case ']':
                code.next();
                return new Token("FECHA_COLCHETE", "]");

            case ',':
                code.next();
                return new Token("VIRGULA", ",");
            case ';':
                code.next();
                return new Token("PONTO_E_VIRGULA", ";");

            case CharacterIterator.DONE:
                return new Token("EOF", "$");
            default:
                return null;
        }
    }
}
