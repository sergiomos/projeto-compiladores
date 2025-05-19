package Lexico;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;

    public Lexer(String data) {
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(data);
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
        StringBuilder contexto = new StringBuilder();
        int pos = code.getIndex();
        code.setIndex(Math.max(0, pos - 10));
        for (int i = 0; i < 20 && code.current() != CharacterIterator.DONE; i++) {
            contexto.append(code.current());
            code.next();
        }
        code.setIndex(pos);
        throw new RuntimeException("Erro léxico: token não reconhecido '" + code.current() +
            "' na posição " + pos + "\nContexto: ..." + contexto.toString() + "...");

    }
}
