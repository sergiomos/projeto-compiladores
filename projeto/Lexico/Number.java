package Lexico;

import java.text.CharacterIterator;

public class Number extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        System.out.println("NUM");
        String number = readNumber(code);
        String type = "INT";

        if (code.current() == '.') {
            type = "FLOAT";
            number += '.';
            code.next();
            number += readNumber(code);
        }

        if (isTokenSeparator(code)) {
            return new Token(type, number);
        }
        return null;
    }

    private String readNumber(CharacterIterator code) {
        String number = "";

        while (Character.isDigit(code.current())) {
            number += code.current();
            code.next();
        }

        return number;
    }

}
