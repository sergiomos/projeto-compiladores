package Lexico;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;
    private int currentLine;

    public Lexer(String data) {
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(data);
        this.currentLine = 1;
        afds.add(new Symbols());
        afds.add(new Reserved());
        afds.add(new RelationalOperator());
        afds.add(new LogicalOperator());
        afds.add(new AssignOperator());
        afds.add(new MathOperator());
        afds.add(new Number());
        afds.add(new Text());
        afds.add(new Identificador());
    }

    public void skipWhiteSpace() {
        while (code.current() == ' ' || code.current() == '\n') {
            if (code.current() == '\n') {
                currentLine++;
            }
            code.next();
        }
    }

    public List<Token> getTokens() {
        Token t;
        do {
            skipWhiteSpace();
            t = searchNextToken();
            if (t == null)
                error();
            t.setLine(currentLine);
            tokens.add(t);
        } while (t.getType() != "EOF");

        return tokens;
    }

    private Token searchNextToken() {
        int pos = code.getIndex();

        for (AFD afd : afds) {
            Token t = afd.evaluate(code);
            if (t != null)
                return t;
            code.setIndex(pos);
        }

        return null;
    }

    public void error() {
        throw new RuntimeException("Error: token not recognized: " + code.current());
    }
}
