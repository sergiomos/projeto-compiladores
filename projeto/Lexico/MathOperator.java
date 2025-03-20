package Lexico;

import java.text.CharacterIterator;

public class MathOperator extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        System.out.println("MATH");
        switch (code.current()) {
            case '+':
                code.next();
                return new Token("PLUS", "+");

            case '-':
                code.next();
                return new Token("SUB", "-");

            case '/':
                code.next();
                return new Token("DIV", "/");

            case '*':
                code.next();
                return new Token("MULT", "*");

            case CharacterIterator.DONE:
                return new Token("EOF", "$");
            default:
                return null;
        }
    }
}
