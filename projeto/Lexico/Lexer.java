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
        tokens =  new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(data);
        afds.add(new MathOperator());
        afds.add(new Number());
        afds.add(new Text());
    }

    public void skipWhiteSpace(){
        while(code.current() == ' ' || code.current() == '\n') {
            code.next();
        }
    }

    public List<Token> getTokens() {
        Token t;
        do{
            skipWhiteSpace();
            t = searchNextToken();
            if(t == null) error();
            tokens.add(t);
        } while (t.getType() != "EOF");
        
        return tokens;
    }

    private Token searchNextToken() {
        int pos = code.getIndex();

        for (AFD afd : afds) {
            Token t = afd.evaluate(code);
            if (t != null) return t;
            code.setIndex(pos);
        }

        return null;
    }

    public void error() {
        throw new RuntimeException("Error: token not recognized: " + code.current());
    }
}
