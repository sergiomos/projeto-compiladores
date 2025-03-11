package Lexico;

import java.text.CharacterIterator;

public class Text extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        String str = readString(code);

        if (isTokenSeparator(code)) {
            return new Token("STRING", str);
        }

        return null;
    }

    private String readString(CharacterIterator code) {
        String str = "";

        while (Character.isLetter(code.current()) || code.current() == '"') {
            str += code.current();
            code.next();
        }

        return str;
    }

}
